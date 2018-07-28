import java.util.Scanner;

/**
 * Course class
 * @author dariushnoorzada
 * @version 1.1
 */
public class Course implements DirectlyRelatable {
    Scanner MyKey=new Scanner(System.in);

    /**
     * Get the preRequisite ID
     * @return Prerequ ID
     */
    public String getPreReqID() {
        return preReqID;
    }

    /**
     * set the preReqID
     * @param preReqID new preReq ID
     */
    public void setPreReqID(String preReqID) {
        this.preReqID = preReqID;
    }
    /**
     * Get the credit
     * @return credit
     */
    public double getCredit() {

        return credit;
    }
    /**
     * set the credit
     * @param credit new credit
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }
    /**
     * Get the course name
     * @return course name
     */
    public String getCourseName() {

        return courseName;
    }
    /**
     * set the course name
     * @param courseName new course name
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    /**
     * Get the course ID
     * @return Course ID
     */
    public String getCourseID() {

        return courseID;
    }
    /**
     * set the courseID
     * @param courseID new course ID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    /**
     * Get the coRequisite ID
     * @return corequ ID
     */
    public String getCoReqID() {

        return coReqID;
    }
    /**
     * set the coReqID
     * @param coReqID new coReq ID
     */
    public void setCoReqID(String coReqID) {
        this.coReqID = coReqID;
    }
    private String courseID;
    private String courseName;
    private double credit;
    private String preReqID;
    private String coReqID;
    @Override
    /**
     * Defines directly related method
     */
    public boolean isDirectlyRelate(Course C) {
        return !((this.preReqID.equals(C.courseID)||this.coReqID.equals(C.courseID))||(C.preReqID.equals(this.preReqID)||C.coReqID.equals(this.coReqID)));
    }

    /**
     * Constructor for course
     * @param courseID Course ID
     * @param courseName name
     * @param credit credit number
     * @param preReqID prequisites
     * @param coReqID corequisites
     */
    public Course(String courseID, String courseName,double credit,String preReqID,String coReqID){
        this.courseID=courseID;
        this.courseName=courseName;
        this.credit=credit;
        this.preReqID=preReqID;
        this.coReqID=coReqID;
    }

    /**
     * Constructor copy
     * @param courseID course ID
     * @param original Course object
     */
    public Course(String courseID,Course original){
        this.courseName=original.courseName;
        this.credit=original.credit;
        this.preReqID=original.preReqID;
        this.coReqID=original.coReqID;
        this.courseID=courseID;
    }

    /**
     * Clone method
     * @return new clone
     */
    public Course clone(){
        System.out.println("Please enter the ID for the new cloned course: ");
        String IDin=MyKey.next();
        Course obj = new Course(IDin,this.courseName,this.credit,this.preReqID,this.coReqID);
        return obj;
    }

    /**
     * String method for course
     * @return String of info
     */
    public String toString(){
        return ""+courseName+" has ID "+courseID+", weighs "+credit+" credits, has "+preReqID+" as pre-requisites and "+coReqID+" as co-requisites";
    }

    /**
     * equals another object
     * @param obj The object compared to
     * @return boolean result
     */
    public boolean equals(Object obj){
        if (this.getClass()!=obj.getClass()||obj==null)
            return false;
        else{
            Course objC=(Course) obj;
            return (this.courseName.equals(objC.courseName)&&this.coReqID.equals(objC.coReqID)&&this.preReqID.equals(objC.preReqID)&&this.credit==objC.credit);
        }
    }

}
