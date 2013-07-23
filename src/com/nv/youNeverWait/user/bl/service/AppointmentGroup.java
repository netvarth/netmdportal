/**
 * AppointmentGroup.java
 */
package com.nv.youNeverWait.user.bl.service;

import java.util.List;
import com.nv.youNeverWait.rs.dto.AppointmentsDTO;

public interface AppointmentGroup {
public String getName();
public List<AppointmentsDTO> getAppointmentList(String patientId);
}
