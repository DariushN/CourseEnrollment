import java.util.NoSuchElementException;

/**
 * CourseList class with inner Nodes, linked list
 * @author DariushNoorzada
 * @version 1.1
 */
public class CourseList {

    /**
     * Node class for Course linked list
     * @author dariushnoorzada
     * @version 1.1
     */
    public class CourseNode implements Cloneable{
        private CourseNode next;
        private Course course;

        /**
         * Get the course object
         * @return Course object
         */
        public Course getCourse() {
            return course;
        }

        /**
         * Set the course
         * @param course Course object
         */

        public void setCourse(Course course) {
            this.course = course;
        }

        /**
         * Get the next node
         * @return Next node
         */
        public CourseNode getCn() {

            return next;
        }

        /**
         * Set the next node
         * @param next next Node
         */

        public void setCn(CourseNode next) {
            this.next = next;
        }

        /**
         * Default constructor
         */

        public CourseNode(){
            next=null;
            course=null;
        }

        /**
         * Parameterized constructor
         * @param course Course object
         * @param next Next object linked
         */
        public CourseNode(Course course, CourseNode next){
            this.next=next;
            this.course=course;
        }

        /**
         * Parameterized constructor
         * @param object Coursenode object
         */
        public CourseNode(CourseNode object){
            this.next=object.next;
            this.course=object.course.clone();
        }

        /**
         * Clone method to return similar
         * @return Clone of object
         */
        public Object clone(){
            try {
                return (CourseNode)super.clone();
            } catch (CloneNotSupportedException e){
                System.out.println("Clone not supported!");
                return null;
            }
        }

    }

    private CourseNode head;

    /**
     * Return size of linked list
     * @return size
     */
    private int size(){
        int ctr=0;
        CourseNode temp=head;
        while(temp!=null){
            ctr++;
            temp=temp.next;
        }
        return ctr;
    }

    /**
     * Default constructor
     */
    public CourseList(){
        head=null;
    }

    /**
     * Parameterized constructor
     * @param object CourseList obj
     */
    public CourseList(CourseList object){
        if (object.head==null)
            head=null;
        else{
            head=null;
            CourseNode t1= object.head;
            CourseNode t2,t3;
            t2=t3=null;
            while (t1!=null){
                if (head==null){
                    t2=new CourseNode(object.head);
                    head=t2;
                }
                else{
                    t3=new CourseNode(t1);
                    t2.next=t3;
                    t2=t3;
                }
                t1=t1.next;
            }
            t2=t3=null;
        }
    }

    /**
     * Clone method
     * @return clone of list
     */
    public CourseList clone(){
        return new CourseList(this);
    }

    /**
     * Adds object to start of linked list
     * @param c Course Object
     */
    public void addToStart(Course c){
        CourseNode c1= new CourseNode(c,head);
        head=c1;
        c1=null;
    }

    /**
     * inserts course object at index
     * @param c Course object
     * @param index index
     */
    public void insertAtIndex(Course c, int index){
        try{
            if (index<0||index>size())
                throw new NoSuchElementException();
            CourseNode t1=head;
            CourseNode t2=null;
            while (index>1){
                t1=t1.next;
                index--;
            }
            if(index==0){
                addToStart(c);
            }
            else if(index==size()){
                CourseNode t3 = new CourseNode(c, null);
                t1.next = t3;
                t1 =t2 = t3 = null;
            }
            else {
                t2 = t1.next;
                CourseNode t3 = new CourseNode(c, t2);
                t1.next = t3;
                t1 = t2 = t3 = null;
            }
        }catch (NoSuchElementException e){
            System.out.println("No such element for index! Will now terminate!");
            System.exit(0);
        }
    }

    /**
     * show content
     */
    public void showContent(){
        CourseNode t1=head;
        while (t1!=null) {
            System.out.println(t1.course);
            t1 = t1.next;
        }
    }

    /**
     * delete course object from index
     * @param index number
     */
    public void deleteFromIndex(int index){
        try{
            if (index<0||index>size()-1)
                throw new NoSuchElementException();
            CourseNode t1=head;
            while (index>1){
                t1=t1.next;
                index--;
            }
            if(index==0) {
                head = t1.next;
            }
            else if (index==size()-1){
                t1.next=null;
            }
            else {
                t1.next=t1.next.next;
            }
        }catch (NoSuchElementException e){
            System.out.println("No such element for index! Will now terminate!");
            System.exit(0);
        }
    }

    /**
     * deletes first term
     */
    public void deleteFromStart(){
        deleteFromIndex(0);
    }

    /**
     * Replaces an item of a linked list
     * @param c Course object
     * @param index Number
     */
    public void replaceAtIndex(Course c,int index){
        if (index<0||index>size()-1)
            System.out.println("Invalid index to replace!");
        else {
            CourseNode t1 = head;
            while (index > 1) {
                t1 = t1.next;
                index--;
            }
            CourseNode t2 = new CourseNode(c, t1.next.next);
            t1.next = t2;
        }
    }
    //This is a privacy leaked, cause someone using this method has access directly to the CourseNodes

    /**
     * finds and points to object with courseID
     * @param courseID Course ID
     * @return pointer to object if found
     */
    public CourseNode find(String courseID){
        CourseNode t1=head;
        int ctr=1;

        while (t1!=null){
            if(t1.course.getCourseID().equals(courseID)){
                return t1;
            }
            ctr++;
            t1=t1.next;
        }
        return null;
    }

    /**
     * See if list contains course ID
     * @param courseID Course ID
     * @return boolean
     */
    public boolean contains(String courseID){
        return (find(courseID)!=null);
    }

    /**
     * equals another list
     * @param cl Courselist object
     * @return Boolean result
     */
    public boolean equals(CourseList cl){
        if (cl.size()!=size())
            return false;
        CourseNode t1=head;
        CourseNode c1=cl.head;
        int ctr=0;
        while(t1!=null){
            if (c1.course.equals(t1.course))
                ctr++;
            c1=c1.next;
            t1=t1.next;
        }
        return (ctr==size());
    }
}
