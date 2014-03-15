/**
 * DoctorTestDaoImpl 
 */
package com.nv.youNeverWait.user.pl.impl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.astyanax.MutationBatch;
import com.netflix.astyanax.connectionpool.OperationResult;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import com.netflix.astyanax.model.Column;
import com.netflix.astyanax.model.ColumnFamily;
import com.netflix.astyanax.model.ColumnList;
import com.netflix.astyanax.serializers.StringSerializer;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorViewResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.pl.dao.AstyanaxDao;
import com.nv.youNeverWait.user.pl.dao.DoctorTestDao;

/**
 * @author Luciya
 *
 */
public class DoctorTestDaoImpl extends AstyanaxDao implements DoctorTestDao{
	private static int count=100;
	 private static final Logger LOG = LoggerFactory.getLogger(DoctorTestDaoImpl.class);
	   // public static final String TABLE_NAME = "children";
	 public static final String TABLE_NAME = "netmd";
	    private static final ColumnFamily<String, String> COLUMN_FAMILY = ColumnFamily.newColumnFamily(TABLE_NAME,
	    		StringSerializer.get(), StringSerializer.get(), 
	            StringSerializer.get());

	    public DoctorTestDaoImpl(String host, String keyspace) {
	        super(host, keyspace);
	    }

	    public DoctorTestDaoImpl()
	    {
	    	
	    }
	    @Override
		@Transactional
		public ResponseDTO create(DoctorDetail doctor, HeaderDTO header) {
			ResponseDTO response= new ResponseDTO();
			count++;
			String mychildId=Integer.toString(count);
			DoctorTestDaoImpl childDao = new DoctorTestDaoImpl("192.168.1.91","mykeyspace");
			Map<String, String> aChildRow = new HashMap<String, String>();
	    	aChildRow.put("childId",mychildId);
	    	aChildRow.put("state","AC");
	    	aChildRow.put("zip","99456");
	    	try {
				childDao.write(mychildId, aChildRow);
				LOG.debug("Done");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
	    	response.setSuccess(true);
			return response;
		}

		
		@Override
		public DoctorViewResponseDTO view(int doctorId) {
			DoctorViewResponseDTO response= new DoctorViewResponseDTO();
			String mychildId=Integer.toString(count);
			DoctorTestDaoImpl childDao = new DoctorTestDaoImpl("192.168.1.91","mykeyspace");
			Map<String, String> aChildRow = new HashMap<String, String>();
//	    	aChildRow.put("childId","101");
//	    	aChildRow.put("state","ST");
//	    	aChildRow.put("zip","12345");
	    	try {
	    		//childDao.write("101", aChildRow);
	    		childDao.read(mychildId);
	    		LOG.debug("Done");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
	    	response.setSuccess(true);
			return response;
		}
	
		@Override
		@Transactional
		public ResponseDTO delete(int globalId) {
			ResponseDTO response= new ResponseDTO();
			String mychildId=Integer.toString(count);
			DoctorTestDaoImpl childDao = new DoctorTestDaoImpl("192.168.1.91","mykeyspace");
			Map<String, String> aChildRow = new HashMap<String, String>();
//	    	aChildRow.put("childId","102");
//	    	aChildRow.put("state","TT");
//	    	aChildRow.put("zip","45678");
	    	try {
	    		//childDao.write("102", aChildRow);
	    		childDao.delete(mychildId);
	    		LOG.debug("Done");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				LOG.error(e.getMessage());
				e.printStackTrace();
			}
	    	response.setSuccess(true);
			return response;
		}

		@Override
		@Transactional
		public ResponseDTO update(DoctorDetail doctor, HeaderDTO header) {
			// TODO Auto-generated method stub
			return null;
		}
		
	    public void write(String rowKey, Map<String, String> columns) throws ConnectionException {
	        MutationBatch mutation = this.getKeyspace().prepareMutationBatch();
	        for (Map.Entry<String, String> entry : columns.entrySet()) {
	            mutation.withRow(COLUMN_FAMILY, rowKey).putColumn(entry.getKey(), entry.getValue(), null);
	        }
	       // mutation.withRow(COLUMN_FAMILY, rowKey)
	      //  .putColumn("childId","005")
	       // .putColumn("state","CA")
	      //  .putColumn("zip","94085")
	        ;
	        mutation.execute();
	        LOG.debug("Wrote child [" + rowKey + "]");
	    }

	    public ColumnList<String> read(String rowKey) throws ConnectionException {
	        OperationResult<ColumnList<String>> result = this.getKeyspace().prepareQuery(COLUMN_FAMILY).getKey(rowKey)
	                .execute();
	        ColumnList<String> child = result.getResult();
	        LOG.debug("ColumnsList: " +child);
	        Iterator<Column<String>> colItr = child.iterator();
	        while(colItr.hasNext()){ LOG.debug(colItr.next().getStringValue());}
	     
	        LOG.debug("Read child [" + rowKey + "]");
	        return child;
	    }

	    public void delete(String rowKey) throws ConnectionException {
	        MutationBatch mutation = this.getKeyspace().prepareMutationBatch();
	        mutation.withRow(COLUMN_FAMILY, rowKey).delete();
	        mutation.execute();
	        LOG.debug("Deleted child [" + rowKey + "]");

	    }
	    
	    public static void main(String... args){
	    	try{
	    		DoctorTestDaoImpl childDao = new DoctorTestDaoImpl("localhost","mykeyspace");
		    	
		    	childDao.read("009");
		    	
		    	
	    	}catch(Exception e){
	    		
	    		e.printStackTrace();
	    	}finally{
	    		
	    	}
	    }

		
}
