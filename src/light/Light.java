package light;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

import util.Goal;
import util.Skill;
import blocksworld.BWSkills;
import blocksworld.BWState;

public class Light {

    private static Map<Goal, List<Goal>> domainGoalDecomp;
    private static List<Skill> domainSkills;

    private static void learnHTN(List<Skill> skills,
            Map<Goal, List<Goal>> goalDecomp, Demonstration demo) {
        // TODO
        System.out.println("Demo: " + demo.toString()); // TODO Implement
                                                        // toString for demo
        // Setup domain knowledge;
        domainGoalDecomp = goalDecomp;
        domainSkills = skills;

        Queue<Goal> goals = new LinkedList<Goal>();
        goals.add(demo.goal);

        // 1. Start from the back.
        List<Skill> sequence = demo.skillSequence;
        Collections.reverse(sequence);
        // TODO: Print sequence and print demo.skillSequence

        while (!goals.isEmpty()) {

            // 1.0 Print out current goal:
            System.out.println("Current goals: " + goals);

            Goal currentGoal = goals.remove();
            // 1.1 Skill-Chaining: Check if the current Skill result in the
            // current goal

            Goal newGoal = skillChaining(sequence, currentGoal, demo.initState);

            if (newGoal == null) {
                // concept chaining
                List<Goal> newGoals = conceptChaining(sequence, currentGoal);
                // TODO: Change below to Assert...
                if (newGoals == null) {
                    // Something is wrong...
                    // This means that the current goal does not have a
                    // decomposition AND no previous skills achieves this state.
                    throw new IllegalStateException("");
                } else {
                    for (Goal g : newGoals) {
                        goals.add(g); // Add new goals to end of the queue.
                    }
                }
            } else if (newGoal.equals(new Goal("StartState", null))) {
                System.out.println("...Start State Reached, "
                        + "no need to add more goal");
            } else {
                goals.add(newGoal);
            }
        }

    }

    private static List<Goal> conceptChaining(List<Skill> sequence,
            Goal currentGoal) {
        // 1.2 Concept Chaining if cannot find skill that achieves goal: by
        // decomposing the concept into many smaller concepts.
        List<Goal> decomposition = null;
        Goal coGoal = null;
        for (Goal g : domainGoalDecomp.keySet()) {
            if (g.getName().equals(currentGoal.getName())) {
                coGoal = g;
                decomposition = domainGoalDecomp.get(g);
                break;
            }
        }

        if (decomposition == null) {
            return null;
        }

        // update args
        List<String> template = coGoal.getArgs();
        List<String> mappTo = currentGoal.getArgs();
        for (Goal newGoal : decomposition) {
            if (newGoal.getNumArgs() > 0) {
                ArrayList<String> newArgs = new ArrayList<String>();
                for (String a : newGoal.getArgs()) {
                    newArgs.add(mappTo.get(template.indexOf(a)));
                }
                newGoal.setArgs(newArgs);
            }
        }

        return decomposition;
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
    private static Goal skillChaining(List<Skill> sequence, Goal currentGoal,
            BWState initState) {

        // Look at previous sequences
        for (Skill currentSkill : sequence) {
            if (currentSkill.getEffects().contains(currentGoal)) {
                return currentSkill.getPrecondition();
            }
        }

        // Check initial condition
        if (initState.getConcepts().contains(currentGoal)) {
            return new Goal("StartState", null);
        }

        return null;
    }

    public static class Demonstration {
        public BWState initState;
        public Goal goal;
        public List<Skill> skillSequence;

        public Demonstration(BWState s, Goal g, List<Skill> sequence) {
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

        // First: Make a list of all possible goals
        // Redo: of concepts and decomposition
        Map<Goal, List<Goal>> goalDecomp = new HashMap<Goal, List<Goal>>();
        /**
         * Goals: unstackable, stackable, clear, holding, on, hand-empty,
         * putdownable
         */
        // Define primitive goals
        goalDecomp.put(new Goal("on"), null);
        goalDecomp.put(new Goal("clear"), null);
        goalDecomp.put(new Goal("holding"), null);
        goalDecomp.put(new Goal("hand-empty"), null);

        ArrayList<String> genericUnstackArgs = new ArrayList<String>(
                Arrays.asList("X", "Y"));
        List<Goal> list = new ArrayList<Goal>();
        list.add(new Goal("on", genericUnstackArgs));
        list.add(new Goal("clear", genericUnstackArgs.subList(0, 1)));
        list.add(new Goal("hand-empty", null));
        goalDecomp.put(new Goal("unstackable", genericUnstackArgs), list);

        ArrayList<String> putArgs = new ArrayList<String>(Arrays.asList("X", "Y"));
        List<Goal> list2 = new ArrayList<Goal>();
        list2.add(new Goal("clear", putArgs.subList(1, 2)));
        list2.add(new Goal("holding", putArgs.subList(0, 1)));
        goalDecomp.put(new Goal("putdownable", putArgs), list2);
        
        // Given: a set of training problems, each of which specifies an initial
        // state and a goal;

        // Define the initial State
        BWState initState = new BWState();
        ArrayList<String> unstackArgs = new ArrayList<String>(Arrays.asList(
                "C", "B"));
        initState.addConcept(new Goal("unstackable", unstackArgs));
        initState.addConcept(new Goal("hand-empty", null));
        ArrayList<String> clearArgs = new ArrayList<String>(Arrays.asList("C"));
        initState.addConcept(new Goal("clear", clearArgs));
        initState.addConcept(new Goal("on", unstackArgs));
        ArrayList<String> onBAArgs = new ArrayList<String>(Arrays.asList("B",
                "A"));
        initState.addConcept(new Goal("on", onBAArgs));

        // Define the goal
        ArrayList<String> args1 = new ArrayList<String>(Arrays.asList("A"));
        Goal goal = new Goal("clear", args1);

        // Given: for each training problem, a sequence of operator instances
        // that achieves the goal form the initial state;
        List<Skill> skillSequence = new ArrayList<Skill>();
        ArrayList<String> args2 = new ArrayList<String>(Arrays.asList("C", "B"));
        skillSequence.add(new BWSkills.UnStack(args2));
        ArrayList<String> args3 = new ArrayList<String>(
                Arrays.asList("C", "T1"));
        skillSequence.add(new BWSkills.PutDown(args3));
        ArrayList<String> args4 = new ArrayList<String>(Arrays.asList("B", "A"));
        skillSequence.add(new BWSkills.UnStack(args4));

        Demonstration demo = new Demonstration(initState, goal, skillSequence);
        learnHTN(skills, goalDecomp, demo);
    }

}
