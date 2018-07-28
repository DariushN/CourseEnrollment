public class Driver {
    public static void main(String[] args){
        CourseList c=new CourseList();
        Course c1=new Course("3432","BIOL206",4,"4323","423423");
        Course c2=new Course("3433","BIOL207",4,"4323","423423");
        Course c3=new Course("3434","BIOL208",4,"4323","423423");
        Course c4=new Course("3435","BIOL209",4,"4323","423423");
        Course c5=new Course("3436",c1);
        c.addToStart(c1);
        c.insertAtIndex(c2,1);
        c.insertAtIndex(c3,2);
        c.insertAtIndex(c4,0);
        CourseList s=new CourseList();
        s.addToStart(c1);
        s.insertAtIndex(c2,1);
        s.insertAtIndex(c3,2);
        s.insertAtIndex(c4,0);
        s.deleteFromIndex(3);
        System.out.println(s.contains("343"));
        c.insertAtIndex(c1.clone(),3);
        c.insertAtIndex(c5,4);
        c.showContent();
        Course COMP108 = new Course("COMP108", "Computer_Science_Industrial_Experience_Reflective_Learning_I", 3, "", "");
        Course COMP201 = new Course("COMP201", "Introduction_to_Computing", 3, "MATH201", "");
        Course COMP248 = new Course("COMP248", "Object-Oriented_Programming_I", 3.5, "MATH204", "COMP201");
        Course COMP228 = new Course("COMP228", "System_Hardware", 4, "COMP248", "");

        System.out.println("\n=====Testing the isDirectlyRelated Method========");
        System.out.println("Are " + COMP108.getCourseID() + " and " + COMP201.getCourseID() + " directly related?: " + COMP108.isDirectlyRelate(COMP201));
        System.out.println("Are " + COMP248.getCourseID() + " and " + COMP228.getCourseID() + " directly related?: " + COMP248.isDirectlyRelate(COMP228));
    }
}
