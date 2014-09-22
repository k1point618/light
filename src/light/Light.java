package light;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import util.ComplexConcept;
import util.Concept;
import util.Skill;
import blocksworld.BWConcepts;
import blocksworld.BWSkills;
import blocksworld.BWState;

public class Light {

    private static List<Concept> domainConcepts;
    private static List<Skill> domainSkills;

    private static void learnHTN(List<Skill> skills, List<Concept> concepts,
            Demonstration demo) {
        // TODO
        System.out.println("Demo: " + demo.toString()); // TODO Implement
                                                        // toString for demo
        // Setup domain knowledge;
        domainConcepts = concepts;
        domainSkills = skills;

        Queue<Concept> goals = new LinkedList<Concept>();
        
        // 1. Start from the back.
        List<Skill> sequence = demo.skillSequence;
        Collections.reverse(sequence);
        // TODO: Print sequence and print demo.skillSequence

        while (!goals.isEmpty()) {

            Concept currentGoal = goals.remove();
            // 1.1 Skill-Chaining: Check if the current Skill result in the
            // current goal

            Concept newGoal = skillChaining(sequence, currentGoal);

            if (newGoal == null) {
                // concept chaining
                List<Concept> newGoals = conceptChaining(sequence, currentGoal);
                if(newGoals == null) {
                    //Something is wrong...
                    // This means that the current goal does not have a decomposition AND no previous skills achieves this state.
                }
            } else {
                goals.add(newGoal);
            }
        }

    }

    private static List<Concept> conceptChaining(List<Skill> sequence,
            Concept currentGoal) {
        // 1.2 Concept Chaining if cannot find skill that achieves goal: by
        // decomposing the concept into many smaller concepts.
        for (Concept c : domainConcepts) {
            if (currentGoal.equals(c)) {
                // Kind of uncessary, could get decomposition directly
                // from current goal
                if (c instanceof ComplexConcept) {
                    return ((ComplexConcept) c).getPositives();
                }
            }
        }
        return null;
    }

    /**
     * Skill Chaining looks at the sequence of demonstrated skills in reverse.
     * With a current goal, it finds the skill that is responsible of achieving
     * that goal.
     * 
     * @param sequence
     *            : is the seqeunce of skills in the demonstration reversed.
     * @param currentGoal
     *            : is the goal that we are trying to explain.
     * @return
     */
    private static Concept skillChaining(List<Skill> sequence,
            Concept currentGoal) {
        for (Skill currentSkill : sequence) {
            if (currentSkill.getEffects().contains(currentGoal)) {
                return currentSkill.getPrecondition();
            }
        }
        return null;
    }

    public static class Demonstration {
        public BWState initState;
        public Concept goal;
        public List<Skill> skillSequence;

        public Demonstration(BWState s, Concept g, List<Skill> sequence) {
            this.initState = s;
            this.goal = g;
            this.skillSequence = sequence;
        }

        @Override
        public String toString() {
            return "Demonstration [\ninitState=" + initState + ", \ngoal="
                    + goal + ", \nskillSequence=" + skillSequence + "]";
        }

    }

    public static void main(String[] args) {
        // Given: A set of operators that produce predictable effects under
        // known conditions
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(new BWSkills.PutDown());
        skills.add(new BWSkills.Stack());
        skills.add(new BWSkills.UnStack());

        // Given: A set of goal-decompositions.
        List<Concept> concepts = new ArrayList<Concept>();
        concepts.add(new BWConcepts.Clear());
        concepts.add(new BWConcepts.HandEmpty());
        concepts.add(new BWConcepts.On());
        concepts.add(new BWConcepts.UnStackable());

        // Given: a set of training problems, each of which specifies an initial
        // state and a goal;

        // Define the initial State
        BWState initState = new BWState();
        String[] unstackArgs = { "C", "B" };
        initState.addConcept(new BWConcepts.UnStackable(unstackArgs));
        initState.addConcept(new BWConcepts.HandEmpty(null));
        String[] clearArgs = { "C" };
        initState.addConcept(new BWConcepts.Clear(clearArgs));
        initState.addConcept(new BWConcepts.On(unstackArgs));
        String[] onBAArgs = { "B", "A" };
        initState.addConcept(new BWConcepts.On(onBAArgs));

        // Define the goal
        String[] args1 = { "A" };
        Concept goal = new BWConcepts.Clear(args1);

        // Given: for each training problem, a sequence of operator instances
        // that achieves the goal form the initial state;
        List<Skill> skillSequence = new ArrayList<Skill>();
        String[] args2 = { "C", "B" };
        skillSequence.add(new BWSkills.UnStack(args2));
        String[] args3 = { "C", "T1" };
        skillSequence.add(new BWSkills.PutDown(args3));
        String[] args4 = { "B", "A" };
        skillSequence.add(new BWSkills.UnStack(args4));

        Demonstration demo = new Demonstration(initState, goal, skillSequence);
        learnHTN(skills, concepts, demo);
    }

}
