//worked with Miki Sugita, Chris Jeon, and Sebastien Nunes

package A2;
import java.util.*;

class Assignment implements Comparator<Assignment> {
    int number;
    int weight;
    int deadline;


    protected Assignment() {
    }

    protected Assignment(int number, int weight, int deadline) {
        this.number = number;
        this.weight = weight;
        this.deadline = deadline;
    }


    /**
     * This method is used to sort to compare assignment objects for sorting.
     * The way you implement this method will define which order the assignments appear in when you sort.
     * Return -1 if a1 should appear after a2
     * Return 1 if a1 should appear before a2
     * Return 0 if a1 and a2 are equivalent
     */
    @Override
    public int compare(Assignment a1, Assignment a2) {
        //YOUR CODE GOES HERE, DONT FORGET TO EDIT THE RETURN STATEMENT

        //if deadline of a1 is more than deadline of a2
        //return 1 because a1 should appear later than a2 in list
        if (a1.weight > a2.weight) {
            return -1;
        }

        //if weight of a2 is more than weight of a1
        //return -1 because a2 should appear later than a1 in list
        else if (a2.weight > a1.weight) {
            return 1;
        }

        //if the weight is the same for both assignments
        //return 0 because they are considered equal in this case.
        else {
            return 0;
        }

    }
}


public class HW_Sched {
    ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
    int m;
    int lastDeadline = 0;

    protected HW_Sched(int[] weights, int[] deadlines, int size) {
        for (int i=0; i<size; i++) {
            Assignment homework = new Assignment(i, weights[i], deadlines[i]);
            this.Assignments.add(homework);
            if (homework.deadline > lastDeadline) {
                lastDeadline = homework.deadline;
            }
        }
        m =size;
    }


    /**
     *
     * @return Array where output[i] corresponds to when assignment #i will be completed. output[i] is 0 if assignment #i is never completed.
     * The homework you complete first will be given an output of 1, the second, 2, etc.
     */
    public int[] SelectAssignments() {
        // Use the following command to sort your Assignments:
        // Collections.sort(Assignments, new Assignment());
        // This will re-order your assignments. The resulting order will depend on how the compare function is implemented
        Collections.sort(Assignments, new Assignment());

        //Initializes the homeworkPlan, which you must fill out and output
        int[] homeworkPlan = new int[Assignments.size()];

        //Fills the array HomeworkPlan with values of -1
        // which represents an empty slot.
        Arrays.fill(homeworkPlan, -1);

        //Iterate through Assignments array
        //checks if there is an empty slot, add value to homeworkPlan
        for (int i = 0; i < Assignments.size(); i++) {

            //value in deadline index for specific assignment
            int val = homeworkPlan[Assignments.get(i).deadline-1];

            if (val == -1) {

                homeworkPlan[Assignments.get(i).deadline-1] = Assignments.get(i).number;

            }

        }

        //Change the -1s to 0s to represent not completed assignments.
        for (int j = 0; j < homeworkPlan.length; j++) {
            if (homeworkPlan[j] == -1) {
                homeworkPlan[j] = 0;
            }
        }

        return homeworkPlan;
    }

}
	



