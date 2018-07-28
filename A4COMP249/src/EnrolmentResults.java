import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The purpose of this class is to create a read and output the Enrollment results
 * @author Dariush Noorzada
 * @version 1.1
 */
public class EnrolmentResults {
    /**
     * Driver class
     * @param args Default driver parameter
     */
    public static void main (String[] args){
        System.out.println("Welcome!");
        CourseList c1=new CourseList();
        CourseList c2=new CourseList();
        Scanner sc=null;
        Scanner MyKey=new Scanner(System.in);
        Scanner sc1=null;
        try {
            sc = new Scanner(new FileInputStream("Syllabus.txt"));
        }catch (FileNotFoundException e){
            System.out.println("File not found! Program will now terminate!");
            System.exit(0);
        }
        int ctr=0;
        while (sc.hasNextLine()){
            String courseID=sc.next();
            String courseName=sc.next();
            double credit=sc.nextDouble();
            String temp=sc.next();
            String preRequID=sc.nextLine().replace("\t","");
            temp=sc.next();
            String coRequID=sc.nextLine().replace("\t","");
            if (!c1.contains(courseID)) {
                c1.insertAtIndex(new Course(courseID, courseName, credit, preRequID, coRequID), ctr);
                ctr++;
            }
            else
                System.out.println("Duplicate entry for "+courseID+" deleted!");
        }
        ArrayList<String> requestedC=new ArrayList();
        ArrayList<String> completedC=new ArrayList();
        System.out.println("Please enter the name of the file to open: ");
        String fileName=MyKey.next();
        try{
            sc1=new Scanner(new FileInputStream(fileName));

        }catch (FileNotFoundException e){
            System.out.println("File not found! Will now terminate!");
            System.exit(0);
        }
        sc1.next();
        while (sc1.hasNextLine()){
            String next=sc1.next();
            if (next.equals("Requested"))
                break;
            completedC.add(next);
        }
        while (sc1.hasNextLine()){
            requestedC.add(sc1.next());
        }
        ArrayList<CourseList.CourseNode> nodeList=new ArrayList<>();
        for (int i=0;i<requestedC.size();i++){
            nodeList.add(c1.find(requestedC.get(i)));
        }
        if(requestedC.size()==0){
            System.out.println("No enrolment courses found");
        }
        else if(completedC.size()==0){
            for (int i=0;i<requestedC.size();i++)
                System.out.println("Student can't enroll in class "+requestedC.get(i)+" as there is not sufficient background");
        }
        else {
            for(int i=0;i<nodeList.size();i++){
                if(nodeList.get(i).getCourse().getCoReqID().equals("")&&nodeList.get(i).getCourse().getPreReqID().equals(""))
                    System.out.println("Student may enroll in "+nodeList.get(i).getCourse().getCourseID()+" as there is no pre or co-requisites");
                else if(nodeList.get(i).getCourse().getCoReqID().equals("")&&!nodeList.get(i).getCourse().getPreReqID().equals("")){
                    int ctrs=0;
                    for (int y=0;y<completedC.size();y++) {
                        if (completedC.get(y).equals(nodeList.get(i).getCourse().getPreReqID()))
                            ctrs++;
                    }
                    if (ctrs>0)
                        System.out.println("Student may enroll in "+nodeList.get(i).getCourse().getCourseID()+" as he/she has completed the prequisite "+nodeList.get(i).getCourse().getPreReqID());
                    else
                        System.out.println("Student may not enroll in "+nodeList.get(i).getCourse().getCourseID()+" as he/she has not completed the prequisite "+nodeList.get(i).getCourse().getPreReqID());
                    ctrs=0;
                }
                else if(!nodeList.get(i).getCourse().getCoReqID().equals("")&&nodeList.get(i).getCourse().getPreReqID().equals("")){
                    int ctrs=0;
                    for (int y=0;y<completedC.size();y++) {
                        if (completedC.get(y).equals(nodeList.get(i).getCourse().getCoReqID())||nodeList.get(i).getCourse().getCoReqID().equals(requestedC.get(y)))
                            ctrs++;
                    }
                    if (ctrs>0)
                        System.out.println("Student may enroll in "+nodeList.get(i).getCourse().getCourseID()+" as he/she has or is completing the corequisite "+nodeList.get(i).getCourse().getCoReqID());
                    else
                        System.out.println("Student may not enroll in "+nodeList.get(i).getCourse().getCourseID()+" as he/she has not or is not completing the corequisite "+nodeList.get(i).getCourse().getCoReqID());
                    ctrs=0;
                }
                else if(!nodeList.get(i).getCourse().getCoReqID().equals("")&&!nodeList.get(i).getCourse().getPreReqID().equals("")){
                    int ctrs=0;
                    for (int y=0;y<completedC.size();y++) {
                        if (completedC.get(y).equals(nodeList.get(i).getCourse().getCoReqID())||nodeList.get(i).getCourse().getCoReqID().equals(requestedC.get(y)))
                            ctrs++;
                    }
                    for (int y=0;y<completedC.size();y++) {
                        if (completedC.get(y).equals(nodeList.get(i).getCourse().getPreReqID()))
                            ctrs++;
                    }
                    if (ctrs>1)
                        System.out.println("Student may enroll in "+nodeList.get(i).getCourse().getCourseID()+" as he/she has completed or completing the prequisite "+nodeList.get(i).getCourse().getPreReqID()+" and corequisite "+nodeList.get(i).getCourse().getCoReqID());
                    else
                        System.out.println("Student may not enroll in "+nodeList.get(i).getCourse().getCourseID()+" as he/she has not completed or completing the prequisite "+nodeList.get(i).getCourse().getPreReqID()+" or corequisite "+nodeList.get(i).getCourse().getCoReqID());
                    ctrs=0;
                }
            }
        }

        do{
            System.out.println("Please enter a course ID to look for: ");
            String IDin=MyKey.next();
            if(c1.contains(IDin))
                System.out.println(c1.find(IDin).getCourse());
            System.out.println("Would you like to do another search?(Yes/No)");
            if (MyKey.next().equalsIgnoreCase("No"))
                break;
        }while (true);
        Course COMP108 = new Course("COMP108", "Computer_Science_Industrial_Experience_Reflective_Learning_I", 3, "", "");
        Course COMP201 = new Course("COMP201", "Introduction_to_Computing", 3, "MATH201", "");
        Course COMP248 = new Course("COMP248", "Object-Oriented_Programming_I", 3.5, "MATH204", "COMP201");
        Course COMP228 = new Course("COMP228", "System_Hardware", 4, "COMP248", "");

        System.out.println("\n=====Testing the isDirectlyRelated Method========");
        System.out.println("Are " + COMP108.getCourseID() + " and " + COMP201.getCourseID() + " directly related?: " + COMP108.isDirectlyRelate(COMP201));
        System.out.println("Are " + COMP248.getCourseID() + " and " + COMP228.getCourseID() + " directly related?: " + COMP248.isDirectlyRelate(COMP228));
        sc.close();
        sc1.close();
        System.out.println("Thank you!");
    }
}
