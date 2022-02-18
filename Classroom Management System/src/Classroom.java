import java.util.*;

public class Classroom {
    private static final int CLASS_CAPACITY = 15;
    private static final int WAIT_LIST_CAPACITY = 5;
    private static int classCount;
    private static int waitListCount;
    private HashMap<Integer, Student> registeredStudents;
    private HashSet<Integer> enrolledIds;
    private Queue<Integer> waitlistIds;


    public Classroom() {
        this.registeredStudents = new HashMap<>();
        this.enrolledIds = new HashSet<>();
        this.waitlistIds = new LinkedList<>();

    }

    public void registerStudent(Student student) {
        registeredStudents.put(student.getId(), student);
    }

    public void enrollStudent(int id) {

        if (classCount < CLASS_CAPACITY && waitListCount < WAIT_LIST_CAPACITY) {
            enrolledIds.add(id);
            classCount++;
        } else if (classCount >= CLASS_CAPACITY && waitListCount < WAIT_LIST_CAPACITY) {
            waitlistIds.add(id);
            waitListCount++;

        } else if (classCount >= CLASS_CAPACITY && waitListCount >= WAIT_LIST_CAPACITY) {
            //No Action
        }
    }

    public void dropStudent(int id) {

        if (enrolledIds.contains(id)) {
            enrolledIds.remove(id);
            waitlistIds.poll();
            waitListCount--;
        }
    }

    public ArrayList<String> getEnrolledStudents() {
        ArrayList<String> StudentsEnrolled = new ArrayList<>();
        for (int i : enrolledIds) {
            StudentsEnrolled.add(registeredStudents.get(i).getName());
        }

        return StudentsEnrolled;
    }


    public ArrayList<String> getWaitlistedStudents() {

        Stack<String> StudentsWaiting = new Stack<>();
        for (int i : waitlistIds) {
            StudentsWaiting.add((registeredStudents.get(i)).getName());
        }

        //Reverse order
        ArrayList<String> waitListNames = new ArrayList<>();
        for (int i : waitlistIds) {
            waitListNames.add(StudentsWaiting.pop());
        }
        return waitListNames;
    }


    public static void main(String[] args) {
        Classroom classroom = new Classroom();
        if (NAMES.length != IDS.length) {
            throw new RuntimeException("Oops! The NAMES and IDS arrays don't match. Did they get modified?");
        }

        // Register all of the students defined by NAMES and IDS below.
        for (int i = 0; i < NAMES.length; i++) {
            classroom.registerStudent(new Student(NAMES[i], IDS[i]));
        }

        // Attempt to enroll all students. This will go in alphabetical order by student name.
        for (int i = 0; i < IDS.length; i++) {
            classroom.enrollStudent(IDS[i]);
        }

        // Attempt to drop a few students from the class, and re-enroll one.
        classroom.dropStudent(IDS[4]);   // Eli
        classroom.dropStudent(IDS[17]);  // Rupert (not enrolled)
        classroom.dropStudent(IDS[10]);  // Klay
        classroom.enrollStudent(IDS[4]);


        // Print out all enrolled students.
        System.out.println("Enrolled students:");
        for (String studentName : classroom.getEnrolledStudents()) {
            System.out.println(studentName);
        }
        System.out.println();


        // Print out all enrolled students.
        System.out.println("Waitlist:");
        for (String studentName : classroom.getWaitlistedStudents()) {
            System.out.println(studentName);
        }
        System.out.println();
    }

    // List of names and IDs used to generate Student data in main.
    private static final String[] NAMES = {
            "Alice", "Buster", "Carol", "Davante", "Eli", "Fiona", "Gob", "Harold", "Ian", "Jesse", "Klay", "Lindsay",
            "Maebe", "Nelly", "Oscar", "Parmesan", "Queen Latifa", "Rupert", "Serena", "Tobias", "Uma", "Viggo",
            "Wyatt", "Xavier", "Yoda", "Zoey",
    };
    private static final int[] IDS = {
            200, 201, 202, 203, 199, 198, 197, 147, 148, 149, 150, 151, 276,
            275, 274, 273, 272, 233, 234, 235, 236, 237, 172, 171, 170, 169,
    };
}
