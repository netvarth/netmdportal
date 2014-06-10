/**
 * ConfigDaoImpl.java
 * @author netvarth
 *
 * Version 1.0 Aug 30, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.SpecimenTbl;
import com.nv.youNeverWait.pl.entity.TestSpecimenTbl;
import com.nv.youNeverWait.pl.entity.TestTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.DeployConfigDataResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.user.bl.validation.ConfigDataValidator;
import com.nv.youNeverWait.user.pl.dao.ConfigDao;
import java.util.Date;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class ConfigDaoImpl extends GenericDaoHibernateImpl implements ConfigDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext()
	private EntityManager em;
	private ConfigDataValidator configDataValidator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.ConfigDao#parse(java.lang.String)
	 */
	@Override
	@Transactional
	public DeployConfigDataResponseDTO parse(String s) {
		try {
			JsonFactory f = new MappingJsonFactory();
			JsonParser jp = f.createJsonParser(s);
			JsonToken current;
			current = jp.nextToken();
			if (current != JsonToken.START_OBJECT) {
				System.out.println("Error: root should be object: quiting.");
				return null;
			}
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				String fieldName = jp.getCurrentName();
				// move from field name to field value
				current = jp.nextToken();
				if (fieldName.equals("specimen")) {
					if (current == JsonToken.START_ARRAY) {

						// For each of the records in the array
						while (jp.nextToken() != JsonToken.END_ARRAY) {
							// read the record into a tree model,
							// this moves the parsing position to the end of it
							JsonNode node = jp.readValueAsTree();
							// And now we have random access to everything in
							// the object
							/********************************************************************************/
							// ErrorDTO error =
							// configDataValidator.validateSpecimenUid(node.get("uid").getValueAsText());
							// if(error!=null){
							// DeployConfigDataResponseDTO response = new
							// DeployConfigDataResponseDTO();
							// response.setError(error);
							// response.setSuccess(false);
							// return response;
							// }
							SpecimenTbl specimenTbl = (SpecimenTbl) getByUID(
									SpecimenTbl.class, Integer.parseInt(node
											.get("uid").getValueAsText()));
							SpecimenTbl specimen = new SpecimenTbl();
							if (specimenTbl == null) {
								specimen.setUid(Integer.parseInt(node
										.get("uid").getValueAsText()));
								specimen.setName(node.get("name")
										.getValueAsText());
								specimen.setUnit(node.get("unit")
										.getValueAsText());
								save(specimen);
							} else {
								specimenTbl.setUid(Integer.parseInt(node.get(
										"uid").getValueAsText()));
								specimenTbl.setName(node.get("name")
										.getValueAsText());
								specimenTbl.setUnit(node.get("unit")
										.getValueAsText());
								update(specimenTbl);
							}
						}
					}
				}
				if (fieldName.equals("test")) {
					if (current == JsonToken.START_ARRAY) {
						while (jp.nextToken() != JsonToken.END_ARRAY) {
							JsonNode node = jp.readValueAsTree();
							ErrorDTO error = configDataValidator
									.validateTestUid(node.get("uid")
											.getValueAsText());
							if (error != null) {
								DeployConfigDataResponseDTO response = new DeployConfigDataResponseDTO();
								response.setError(error);
								response.setSuccess(false);
								return response;
							}
							TestTbl testTbl = (TestTbl) getByUID(TestTbl.class,
									Integer.parseInt(node.get("uid")
											.getValueAsText()));
							TestTbl test = new TestTbl();
							Date currentDateTime = new Date();
							if (testTbl == null) {
								test.setUid(Integer.parseInt(node.get("uid")
										.getValueAsText()));
								test.setAbbreviation(node.get("abbreviation")
										.getValueAsText());
								test.setTestName(node.get("name")
										.getValueAsText());
								if (node.get("result").toString() != null) {
									test.setResult(node.get("result")
											.toString());
								}

								test.setCreatedDateTime(currentDateTime);
								test.setUpdatedDateTime(currentDateTime);
								test.setActive(true);
								test.setUploadStatus(true);
								save(test);
								/*******************************/
								JsonNode specimenNode = node
										.get("testSpecimen").get(0);
								// for(int
								// index=0;index<specimenNode.size();index++){
								if (specimenNode != null) {
									TestSpecimenTbl testSpecimen = new TestSpecimenTbl();
									testSpecimen.setQuantity(Float
											.parseFloat(specimenNode.get(
													"quantity")
													.getValueAsText()));
									testSpecimen.setTestTbl(test);
									String specmnName = specimenNode.get(
											"specimenName").getValueAsText();
									SpecimenTbl specimenTbl = (SpecimenTbl) getSpecimenByName(
											SpecimenTbl.class, specmnName);
									testSpecimen.setSpecimenTbl(specimenTbl);
									save(testSpecimen);
								}
								// }

							} else {
								testTbl.setUid(Integer.parseInt(node.get("uid")
										.getValueAsText()));
								testTbl.setAbbreviation(node
										.get("abbreviation").getValueAsText());
								testTbl.setTestName(node.get("name")
										.getValueAsText());
								if (node.get("result").toString() != null) {
									testTbl.setResult(node.get("result")
											.toString());
								}
								testTbl.setUploadStatus(true);
								testTbl.setCreatedDateTime(currentDateTime);
								testTbl.setUpdatedDateTime(currentDateTime);
								update(testTbl);
								String specimenName = node.get("testSpecimen")
										.getValueAsText();
								if (!specimenName.equals("null")) {
									String[] specimen = specimenName
											.split("\\,|/");
									for (int index = 0; index < specimen.length; index++) {
										SpecimenTbl specimenTbl = (SpecimenTbl) getSpecimenByName(
												SpecimenTbl.class,
												specimen[index]);
										TestSpecimenTbl testSpecimenTbl = (TestSpecimenTbl) getTestSpecimenById(
												TestSpecimenTbl.class,
												Integer.parseInt(node.get(
														"Test Code")
														.getValueAsText()),
												specimenTbl.getUid());
										if (testSpecimenTbl == null) {
											TestSpecimenTbl testSpecimen = new TestSpecimenTbl();
											testSpecimen.setQuantity(Float
													.parseFloat(node.get(
															"quantity")
															.getValueAsText()));
											testSpecimen.setTestTbl(testTbl);
											testSpecimen
													.setSpecimenTbl(specimenTbl);
											save(testSpecimen);
										} else {
											testSpecimenTbl.setQuantity(Float
													.parseFloat(node.get(
															"quantity")
															.getValueAsText()));
											testSpecimenTbl.setTestTbl(testTbl);
											testSpecimenTbl
													.setSpecimenTbl(specimenTbl);
											update(testSpecimenTbl);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DeployConfigDataResponseDTO response = new DeployConfigDataResponseDTO();
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * @return the configDataValidator
	 */
	public ConfigDataValidator getConfigDataValidator() {
		return configDataValidator;
	}

	/**
	 * @param configDataValidator
	 *            the configDataValidator to set
	 */
	public void setConfigDataValidator(ConfigDataValidator configDataValidator) {
		this.configDataValidator = configDataValidator;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
