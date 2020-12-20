package org.springaop.chapter.four.pointcut;

import java.io.IOException;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springaop.chapter.four.pointcut.annotation.SensitiveOperation;

@Aspect
public class ApplicationPointcutsAspect {

    @Pointcut("execution (public * *(..))")
    public void allPublicMethod() {
    }

    /**
     * A join point is in the web layer if the method is defined in a type in the org.springaop.web package or any sub-package under that.
     */
    @Pointcut("within(org.springaop.web..*)")
    public void inWebLayer() {
    }

    /**
     * A join point is in the service layer if the method is defined in a type in the org.springaop.service package or any sub-package under that.
     */
    @Pointcut("within(org.springaop.service..*)")
    public void inServiceLayer() {
    }

    /**
     * A join point is in the dao layer if the method is defined in a type in the org.springaop.dao e package or any sub-package under that.
     */
    @Pointcut("within(org.springaop.dao..*)")
    public void inResourceLayer() {
    }

    @Pointcut("inWebLayer() || inServiceLayer() || inResourceLayer() ")
    public void inSomeLayer() {
    }

    /* Selecting on Method Names */

    @Pointcut("execution(* swim*(..)) || execution(* play*(..)) || execution(* run(..))")
    public void goodTimesOnTheBeach() {
    }

    /* Selecting on Argument Types */

    @Pointcut("execution(* *())")
    public void allMethodsWithoutArguments() {
    }

    @Pointcut("execution(* *(..))")
    public void allMethodsWithOneOrMoreArgumentRegardlessOfType() {
    }

    @Pointcut("execution(* *(java.lang.Integer, java.lang.String))")
    public void allMethodsWithTwoArgumentFirstIntegerSecondString() {
    }

    @Pointcut("execution(* *(java.lang.Integer,..))")
    public void allMethodsWithFirstArgumentOfTypeIntegerAndZeroOrMoreOtherArguments() {
    }

    @Pointcut("execution(* *(*,java.lang.Integer,..))")
    public void allMethodsWithSecondArgumentOfTypeIntegerAndZeroOrMoreOtherArguments() {
    }

    /* Selecting on Argument Types runtime */

    @Pointcut("args(java.util.Hashtable)")
    public void methodsWithHashtableInsteadOfMap() {
    }

    @Pointcut("@args(*,java.lang.String)")
    public void methodsWithSecondArgumentsOfTypeString() {
    }

    /* Selecting on hierarchy */
    @Pointcut("execution(* com.company.Product+.*(..))")
    public void executionProductHierarchy() {
    }

    @Pointcut("within(com.company.Product +)")
    public void withinProductHierarchy() {
    }

    /* Selecting on return Types */

    @Pointcut("execution (* get*(..))")
    public void methodsWithAllReturnType() {
    }

    @Pointcut("execution (java.lang.Integer get*(..))")
    public void getterMethodsWithIntegerReturnType() {
    }

    @Pointcut("execution (void set*(..))")
    public void setterMethods() {
    }

    /* selecting on throws Exception */

    @Pointcut("execution (* *(..) throws java.lang.IOException)")
    public void methodsThatThrowsIoExceptions() {
    }

    @Pointcut("execution (* *(..) throws java.lang.IllegalThreadStateException)")
    public void methodsThatThrowsIllegalThreadStateException() {
    }

    /* Selecting on annotations */

    @Pointcut("@annotation(org.springaop.chapter.four.pointcut.annotation.SensitiveOperation)")
    public void methodsWithSensitiveOperationAnnotation() {
    }

    @Pointcut("@within(org.springaop.chapter.four.pointcut.annotation.SensitiveOperation)")
    public void methodsWithSensitiveOperationWithinAnnotation() {
    }

    @Pointcut("@annotation(operation)")
    public void methodsForAccounting(SensitiveOperation operation) {
    }

    @Pointcut("@within(operation)")
    public void bankingMethods(SensitiveOperation operation) {
    }

    /* Selecting on target */

    @Pointcut("@target(org.springframework.transaction.annotation.Transactional)")
    public void doInTransactionannotation() {
    }

    @Pointcut("target(org.springaop.service.ExampleService)")
    public void doInTransaction() {
    }

    @Pointcut("this(org.springaop.service.ExampleService)")
    public void exampleServiceTarget() {
    }

    /* composition */

    @Pointcut("execution(void set(..) && args(org.company.Classified)")
    public void compositionExecutionAndArgs() {
    }

    @AfterReturning(value = "execution(com.company.service.*Service get(..))", returning = "output")
    public void interceptOutputParam(String output) {
        // do something
    }

    @AfterThrowing(value = "execution(* startMatch(..) throws java.lang.IOException)", throwing = "ioex")
    public void interceptIOException(IOException ioex) {
        // do something
    }

}
