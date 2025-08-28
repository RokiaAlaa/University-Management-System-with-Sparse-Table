class uniregst
{
    private String[] undoStack;
    private String[] redoStack;
    private int undoTop;
    private int redoTop;
    private final int STACK_SIZE = 100;

    public uniregst() 
    {
        undoStack = new String[STACK_SIZE];
        redoStack = new String[STACK_SIZE];
        undoTop = -1;
        redoTop = -1;
    }
    public void pushundo(String action)
    {
        if(undoTop<STACK_SIZE-1)
        {
            undoStack[++undoTop]=action;
        }
    }

    public String popundo()
    {
        if (undoTop>=0){
            return undoStack[undoTop--];
        }

        return null;
    }
    public void pushredo(String action) 
    {
        if (redoTop < STACK_SIZE - 1) 
        {
            redoStack[++redoTop] = action;
        }
    }
    public String popredo()
    {
        if (redoTop>=0){
            return redoStack[redoTop--];
        }
        return null;
    }
    public void clearRedo() 
    {
        redoTop = -1;
    }

}
class StudentNode
{
    int studentID;
    StudentNode next;
    EnrollmentNode studentHead;

    StudentNode(int ID)
    {
        studentID = ID;
        next = null;
    }
}

class Student
{
    private StudentNode head;
    private StudentNode tail;

    public Student()
    {
        head = tail = null;
    }

    public int getlastStudent()
    {
        StudentNode current = head;
        if (head == null)
        {
            System.out.println("No students");
            return 0;
        }

        while (current.next != null)
        {
            current = current.next;
        }
        return current.studentID;
    }

    public StudentNode getStudent(int id)
    {
        StudentNode temp;
        for (temp = head ; (temp != null) && (temp.studentID != id) ; temp = temp.next);
        return temp;
    }


    public void addStudent(int studentID)
    {

        if (isStudentInList(studentID))
        {
            System.out.println("Student of ID " + studentID + " is already registered.");
        }

        else
        {
            if ((head == null) && (tail == null))
            {
                head = tail = new StudentNode(studentID);
            }
            else
            {
                tail.next = new StudentNode(studentID);
                tail = tail.next;
            }
        }
    }

   public boolean isStudentInList(int ID){        /*this method is for checking if this student was added to the list
                                                        before or not */
  StudentNode tmp;                            
  
    for (tmp = head; (tmp != null)&&(tmp.studentID!=ID); tmp=tmp.next) {
    }
     return (tmp!=null);
}


    private boolean isEmpty()
    {
        return head == null;
    }

    public void removeStudent(int studentID)
    {
        if (!isEmpty())
        {
            if ((head == tail) && (studentID == head.studentID))
            {
                head = tail = null;
            }
            else if (studentID == head.studentID)
            {
                head = head.next;
            }
            else
            {
                StudentNode pred, tmp;
                for (pred = head, tmp = head.next; (tmp != null) && (tmp.studentID != studentID); pred = pred.next, tmp = tmp.next);

                if (tmp != null)
                {
                    pred.next = tmp.next;
                    
                    if (tmp == tail)
                    {
                        tail = pred;
                    }
                }
                else
                {
                    System.out.println("The Student You're looking for does not exist!");
                    return;
                }
            }
            System.out.println("The Student Deleted Successfully");
        }
        else
        {
            System.out.println("No students to delete.");
        }
    }

    public boolean isnormalstudent(int studentID)
    {
        StudentNode tmp = getStudent(studentID);
        if (tmp != null)
        {
            int countCourse = 0;
            EnrollmentNode course;

            for (course = tmp.studentHead; course != null; course = course.nextCourse)  //for loop to count courses
            {
                countCourse++;
            }

            if (countCourse >= 2 && countCourse <= 7)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            System.out.println("Student is not found.");
            return false;
        }
    }

    public boolean isStudentfull(int studentID)
    {
        StudentNode tmp = getStudent(studentID);

        if (tmp != null)
        {
            int countCourse = 0;
            EnrollmentNode course;

            for (course = tmp.studentHead; course != null; course = course.nextCourse)  //for loop to count courses
            {
                countCourse++;
            }

            if (countCourse >= 7)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            System.out.println("Student is not found.");
            return false;
        }
    }
    public void listCoursesByStudent(int studentID) 
    {
        StudentNode tmp = getStudent(studentID);

        if (tmp != null) 
        {
            EnrollmentNode course = tmp.studentHead;
            if (course != null) 
            {
                System.out.println("Courses for student " + studentID +" : ");
                int count = 0;
                while (course != null) 
                {
                    count++;
                    System.out.println("Course "+ count + " ID: " + course.CourseID);
                    course = course.nextCourse;
                }
            } 
            else 
            {
                System.out.println("No courses enrolled for this student");
            }
        }
        else 
        {
            System.out.println("Student is not found");
        }
    }
    public void sortCoursesByID(int studentID) 
    {
        StudentNode tmp = getStudent(studentID);

        if (tmp != null) 
        {
            if (tmp.studentHead == null) 
            {
                System.out.println("No courses enrolled for this student");
                return;
            }
            EnrollmentNode current, index;
            int sortID;

            for (current = tmp.studentHead; current != null; current = current.nextCourse) {
                for (index = current.nextCourse; index != null; index = index.nextCourse) {
                    if (current.CourseID > index.CourseID) 
                    {
                        // Swap course IDs
                        sortID = current.CourseID;
                        current.CourseID = index.CourseID;
                        index.CourseID = sortID;
                    }
                }
            }
            System.out.println("Courses for student ID " + studentID + " have been sorted.");
        } 
        else
        {
            System.out.println("Student is not found");
        }
    }

    public boolean isAlreadyEnrolled(int studentID, int courseID) 
    {
        StudentNode s = getStudent(studentID);
        if (s != null) 
        {
            EnrollmentNode temp = s.studentHead;
            while (temp != null) 
            {
                if (temp.CourseID == courseID) 
                {
                    return true; 
                }
                temp = temp.nextCourse;
            }
        }
        return false;
    }
    

}

class CourseNode
{
    int courseID;
    CourseNode next;
    EnrollmentNode courseHead;

    public CourseNode(int ID)
    {
        courseID = ID;
        next = null;
    }
}

class Course
{
    private CourseNode head;
    private CourseNode tail;

    public Course()
    {
        head = tail = null;
    }

    public CourseNode getCourse (int id)
    {
        CourseNode temp;
        for (temp = head ; (temp != null) && (temp.courseID != id) ; temp = temp.next);
        return temp;
    }

    public void addCourse(int courseID)
    {
        if (isCourseInList(courseID))
        {
            System.out.println("Course of ID " + courseID + " is already added.");
        }

        else
        {
            if ((head == null) && (tail == null))
            {
                head = tail = new CourseNode(courseID);
            }
            else
            {
                tail.next = new CourseNode(courseID);
                tail = tail.next;
            }
        }
    }

    public int getlastCourse()
    {
        CourseNode current = head;

        if (current == null)
        {
            System.out.println("No courses");
            return 0;
        }

        while (current.next != null)
        {
            current = current.next;
        }
        return current.courseID;
    }

    public boolean isCourseInList(int ID){     //this method is for checking if the course was already in the list.
   CourseNode tmp;
     for (tmp = head;(tmp!=null)&&!(tmp.courseID==ID); tmp=tmp.next) {
     }
        return (tmp!=null);
 }

    private boolean isEmpty()
    {
        return head == null;
    }

    public void removeCourse(int courseID)
    {
        if (isEmpty())
        {
            System.out.println("No Courses to delete.");
            return;
        }
        else
        {
            if ((head == tail) && (courseID == head.courseID))
            {
                head = tail = null;
            }
            else if (courseID == head.courseID)
            {
                head = head.next;
            }
            else
            {
                CourseNode pred, tmp;
                for (pred = head, tmp = head.next; (tmp != null) && (tmp.courseID != courseID); pred = pred.next, tmp = tmp.next);

                if (tmp != null)
                {
                    pred.next = tmp.next;
      
                    if (tmp == tail)
                    {
                        tail = pred;
                    }
                    else
                    {
                        System.out.println("The Course You're looking for does not exist!");
                        return;
                    }
                }
            }
            System.out.println("Course deleted successfully!");
        }

    }
    public boolean isfullCourse(int courseID)
    {
        CourseNode tmp = getCourse(courseID);

        if(tmp!=null)
        {
            int countStudent = 0;
            EnrollmentNode student;

            for (student = tmp.courseHead; student != null; student = student.nextStudent)  //for loop to count students in the course.
            {
                countStudent++;
            }

            if (countStudent==30)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void ListStudentsByCourse(int courseID) 
    {
        CourseNode tmp = getCourse(courseID);

        if(tmp == null) 
        {
            System.out.println("Course is not found");
            return;
        }
        
        EnrollmentNode current = tmp.courseHead;

        if (current == null)
        {
            System.out.println("No students found in this course"); 
            return;
        }
        else 
        {
            System.out.println("Students enrolled in the course " + courseID + " are : ");
            int count = 0;
            while (current != null) 
            {
                count++;
                System.out.println("Student "+ count + " ID: " + current.StudentID);
                current = current.nextStudent;
            }
        }  
    }

    public void sortStudentsByID(int courseID) 
    {
        CourseNode tmp = getCourse(courseID);

        if(tmp == null)
        {
            System.out.println("Course is not found");
            return;
        }
        else if(tmp.courseHead == null)
        {
            System.out.println("No students found in this course");
            return;
        }
        
        for (EnrollmentNode i = tmp.courseHead; i.nextStudent != null ; i = i.nextStudent) 
        {
            EnrollmentNode min = i;
            for (EnrollmentNode j = i.nextStudent; j != null ; j = j.nextStudent) 
            {
                if (j.StudentID < min.StudentID) 
                {
                    min = j;

                }
            }
            if (min != i) 
            {
                int tempStudent = i.StudentID;
                int tempCourse = i.CourseID;

                i.StudentID = min.StudentID;
                i.CourseID = min.CourseID;

                min.StudentID = tempStudent;
                min.CourseID = tempCourse;
            }
        } 

        System.out.println("Students enrolled in the course " + courseID + " are sorted by id"); 
    }
}

class EnrollmentNode
{
    int StudentID;
    int CourseID;
    EnrollmentNode nextStudent;
    EnrollmentNode nextCourse;

    public EnrollmentNode(int StudentID , int CourseID)
    {
        this.StudentID = StudentID;
        this.CourseID = CourseID;
        this.nextStudent = null;
        this.nextCourse = null;
    }
}

class Enrollment
{
    private Student student = new Student();
    private Course course = new Course();
    private uniregst history = new uniregst();


    public Enrollment(Student student , Course course)
    {
        this.student = student;
        this.course = course;
    }

    public void enrollStudent(int StudentID , int CourseID)
    {
        if(!course.isCourseInList(CourseID))
        {
            System.out.println("Sorry! The course you're looking for does not exist");
        }
        else if (!student.isStudentInList(StudentID))
        {
            System.out.println("Sorry! The Student you're looking for does not exist");
        }
        else if (student.isStudentfull(StudentID))
        {
            System.out.println("Course limit reached. You can't enroll in more than 7 courses.");
        }
        else if (course.isfullCourse(CourseID))
        {
            System.out.println("This course already has 30 students enrolled. Registration is no longer available.");
        }
        else if (student.isAlreadyEnrolled(StudentID, CourseID)) 
        {
            System.out.println("Student is already enrolled in this course.");
        }
        else
        {
            EnrollmentNode studentNode = new EnrollmentNode(StudentID, CourseID);
            EnrollmentNode courseNode = new EnrollmentNode(StudentID, CourseID);

            StudentNode s = student.getStudent(StudentID);
            studentNode.nextCourse = s.studentHead;
            s.studentHead = studentNode;

            CourseNode c = course.getCourse(CourseID);
            courseNode.nextStudent = c.courseHead;
            c.courseHead = courseNode;

            System.out.println("Student enrolled successfully.");
        }
        history.pushundo("enroll " + StudentID + " " + CourseID);
        history.clearRedo(); 
        
    }

    public void removeEnrollment(int StudentID,int CourseID)
    {
        if(!course.isCourseInList(CourseID))
        {
            System.out.println("Sorry! The course you're looking for does not exist"); 
        }
        else if (!student.isStudentInList(StudentID))
        {
            System.out.println("Sorry! The Student you're looking for does not exist");
        
        }
        else
        {
            StudentNode s = student.getStudent(StudentID);
            CourseNode c = course.getCourse(CourseID);
            EnrollmentNode prevCourse = null , currCourse = s.studentHead;
            while(currCourse != null && (currCourse.CourseID != CourseID))
            {
             prevCourse = currCourse;
             currCourse = currCourse.nextCourse;
            }
            if(currCourse != null)
            {
                if(prevCourse == null)
                {
                    s.studentHead = currCourse.nextCourse;
                }
                else
                {
                    prevCourse.nextCourse = currCourse.nextCourse;
                }
            }
            else
            {
                System.out.println("Enrollment is not found in student's record.");
                return;
            }

            EnrollmentNode prevStudent = null , currStudent = c.courseHead;
            while(currStudent != null && (currStudent.StudentID != StudentID))
            {
                prevStudent = currStudent;
                currStudent = currStudent.nextStudent;
            }
            if(currStudent != null)
            {
                if(prevStudent == null)
                {
                    c.courseHead = currStudent.nextStudent;
                }
                else
                {
                    prevStudent.nextStudent = currStudent.nextStudent;
                }
            }
            else
            {
                System.out.println("Enrollment is not found in course's record.");
                return;
            }

            System.out.println("Enrollment removed successfully.");
        }
        history.pushundo("remove " + StudentID + " " + CourseID);
        history.clearRedo();
    }
    public void undo()
    {
        String action = history.popundo();
        if (action==null)
        {
            System.out.println("There is Nothing to undo");
            return;
        }
        String[] parts=action.split(" ");
        String actionType = parts[0];
        int studentID = Integer.parseInt(parts[1]);
        int courseID = Integer.parseInt(parts[2]);
        if (actionType.equals("enroll"))
        {
            removeEnrollment(studentID, courseID);
            history.pushredo(action);
        } 
        else if (actionType.equals("remove")) 
        {

            enrollStudent(studentID,courseID);
            history.pushredo(action);
        }
    }
    public void redo() 
    {
        String action = history.popredo();
        if (action == null) 
        {
            System.out.println("There is Nothing to redo");
            return;
        }
        String[] parts = action.split(" ");
        String actionType = parts[0];
        int studentID = Integer.parseInt(parts[1]);
        int courseID = Integer.parseInt(parts[2]);
        if (actionType.equals("enroll"))
        {
            enrollStudent(studentID, courseID);
            history.pushundo(action);
        } 
        else if (actionType.equals("remove")) 
        {
            removeEnrollment(studentID, courseID);
            history.pushundo(action);
        }
    }

}


public class University_Management_System
{
    public static void main(String[] args) 
    {
        Student s1 = new Student();
        s1.addStudent(156);
        s1.addStudent(157);
        s1.addStudent(158);
        s1.addStudent(159);
        s1.removeStudent(159);
        
        Course c1 = new Course();
        c1.addCourse(80);
        c1.addCourse(81);
        c1.addCourse(82);
        c1.addCourse(83);
        c1.removeCourse(83);     
        System.out.println(s1.getlastStudent());
        System.out.println(c1.getlastCourse());

        Enrollment e1 = new Enrollment(s1, c1);                
        e1.enrollStudent(158, 80);
        e1.enrollStudent(158, 81);
        e1.enrollStudent(158, 82);
        e1.enrollStudent(156, 80);
        e1.enrollStudent(157, 80);
        c1.sortStudentsByID(80);
        c1.ListStudentsByCourse(80);
        s1.sortCoursesByID(158);
        s1.listCoursesByStudent(158);
        e1.removeEnrollment(158, 80);
        e1.undo();
        e1.redo();
        System.out.println(c1.isfullCourse(83));        
        System.out.println(s1.isnormalstudent(160));
    }
}