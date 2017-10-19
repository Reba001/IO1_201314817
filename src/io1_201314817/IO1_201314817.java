/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io1_201314817;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.math.optimization.GoalType;
import org.apache.commons.math.optimization.OptimizationException;
import org.apache.commons.math.optimization.RealPointValuePair;
import org.apache.commons.math.optimization.linear.LinearConstraint;
import org.apache.commons.math.optimization.linear.LinearObjectiveFunction;
import org.apache.commons.math.optimization.linear.Relationship;
import org.apache.commons.math.optimization.linear.SimplexSolver;
import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
import scpsolver.lpsolver.LinearProgramSolver;
import scpsolver.lpsolver.SolverFactory;
import scpsolver.problems.LinearProgram;
 

/**
 *
 * @author aaper
 */
@SuppressWarnings("deprecation")
public class IO1_201314817 {
    @SuppressWarnings({ "rawtypes", "unchecked"})
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         //describe the optimization problem
        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 5, 10}, 0);
 
        Collection constraints = new ArrayList();
        constraints.add(new LinearConstraint(new double[] { 3, 1}, Relationship.GEQ, 8));// GEQ >=
        constraints.add(new LinearConstraint(new double[] { 0, 4}, Relationship.GEQ, 4));// GEQ >=
        constraints.add(new LinearConstraint(new double[] { 2, 0}, Relationship.LEQ, 2));// LEQ <=
 
        constraints.add(new LinearConstraint(new double[] { 1, 0}, Relationship.GEQ, 0));// GEQ >=
        constraints.add(new LinearConstraint(new double[] { 0, 1}, Relationship.GEQ, 0));// GEQ >=
 
        //create and run solver
        RealPointValuePair solution = null;
        try {
            solution = new SimplexSolver().optimize(f, constraints, GoalType.MINIMIZE, false);// si se quiere maximizar se cambia a MAXIMIZE
        }
        catch (OptimizationException e) {
            e.printStackTrace();
        }
 
        if (solution != null) { 
        //get solution
            double max = solution.getValue();// la solucion optima 
        System.out.println("Opt: " + max);
 
        //print decision variables
        for (int i = 0; i < 2; i++) {
            System.out.print(solution.getPoint()[i] + "\t");// el valor de las variables 
        }
        
            System.out.println("Solucion Minimizacion: "+ (solution.getPoint()[0]*5+solution.getPoint()[1]*10));
    
        }
        System.out.println("");
        LinearProgram lp = new LinearProgram(new double[]{5.0,10.0}); 
        lp.addConstraint(new LinearBiggerThanEqualsConstraint(new double[]{3.0,1.0}, 8.0, "c1")); 
        lp.addConstraint(new LinearBiggerThanEqualsConstraint(new double[]{0.0,4.0}, 4.0, "c2")); 
        lp.addConstraint(new LinearSmallerThanEqualsConstraint(new double[]{2.0,0.0}, 2.0, "c3")); 
        lp.setMinProblem(true); 
        LinearProgramSolver solver  = SolverFactory.newDefault(); 
        double[] sol = solver.solve(lp);
        System.out.println("Solucion Minimizacion: "+ (sol[0]*5+sol[1]*10));
    }
}
