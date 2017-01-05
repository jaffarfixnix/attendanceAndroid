package attendance.fixnix.com.attendanceapp.testApi;

/**
 * Created by akila on 5/1/17.
 */

public class LeaveReqApi {
    private String id;
    private String leaveTypeId;
    private String noOfDays;
    private String fromDate;
    private String toDate;
    private String leaveDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(String leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getNoOfDays(){
        return noOfDays;
    }
    public  void setNoOfDays(String noOfDays){
        this.noOfDays = noOfDays;
    }
    public String getFromDate(){
        return fromDate;
    }
    public  void setFromDate(String fromDate){
        this.fromDate = fromDate;
    }
    public String getToDate(){
        return toDate;
    }
    public  void setToDate(String toDate){
        this.toDate = toDate;
    }
    public String getLeaveDescription(){
        return leaveDescription;
    }
    public  void setLeaveDescription(String leaveDescription){
        this.leaveDescription = leaveDescription;
    }

}


