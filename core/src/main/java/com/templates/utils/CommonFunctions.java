package com.templates.utils;

import com.templates.annotations.Description;
import com.templates.models.DescriptionModel;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

@Slf4j
public class CommonFunctions {

    /**
     * @param millis    time to wait in millis
     */
    public void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * @param seconds   time to wait in seconds
     */
    public void sleeps(int seconds) {
        sleep(seconds * 1000);
    }

    /**
     * Waiting until condition be execution result be "true" and run action between waiting
     *
     * @param condition             expected condition
     *                              Example: exampleString.equal(getPage().mainElement.getText())
     * @param action                action between condition check.
     *                              Example: () -> getPage().refresh()
     * @param iterCycles            max condition execution count
     * @param waitCycleInSeconds    wait time in seconds between condition execution
     * @return                      wait state
     */
    public Boolean waitFor(Callable<Boolean> condition, Runnable action, Integer iterCycles,
                           Integer waitCycleInSeconds) {

        try {
            Integer iter = 0;
            while (iter < iterCycles && !condition.call()) {
                iter++;
                action.run();
                sleeps(waitCycleInSeconds);
            }
            return iter < iterCycles;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new Error("Error during waitFor: " + e.getMessage());
        }
    }

    /**
     * Waiting until condition be execution result be "true" and run action between waiting
     *
     * @param condition             expected condition
     *                              Example: exampleString.equal(getPage().mainElement.getText())
     * @param iterCycles            max condition execution count
     * @param waitCycleInSeconds    wait time in seconds between condition execution
     * @return                      wait state
     */
    public Boolean waitFor(Callable<Boolean> condition, Integer iterCycles, Integer waitCycleInSeconds) {

        return waitFor(condition, () -> toString(), iterCycles, waitCycleInSeconds);
    }

    /**
     * Waiting until condition be executed and return result
     *
     * @param preCondition          expected condition
     *                              Example: exampleString.equal(getPage().mainElement.getText())
     * @param iterCycles            max condition execution count
     * @param postCondition         action for execute between preCondition execution
     * @param waitInSeconds         wait time in seconds between condition execution
     * @return                      wait state
     */
    public <T> T waitFor(Function<T, Boolean> preCondition, Callable<T> postCondition,
                         Integer iterCycles, Integer waitInSeconds) {

        try {
            T result = postCondition.call();
            Integer iter = 0;
            while (iter < iterCycles && !preCondition.apply(result)) {
                iter++;
                result = postCondition.call();
                sleeps(waitInSeconds);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new Error("При выполнении условия выпала ошибка: " + e.getMessage());
        }
    }

    /**
     * Get object content description as text
     *
     * @param object object
     */
    public <T extends DescriptionModel> String getInfo(Object object) {

        if (object == null)
            return "null\n";
        try {
            // Default classes
            final List<Class> standardClasses = Arrays.asList(String.class, Boolean.class, Double.class,
                    Integer.class, Long.class, Float.class, Date.class);
            String objectName = "";
            StringBuilder result = new StringBuilder("");
            // Get class object name
            if (object.getClass().isAnnotationPresent(Description.class)) {
                objectName = object.getClass().getAnnotation(Description.class).value();
                result = result.append("\n_____| ").append(objectName).append("|_____");
            }

            // If object can describe yourself get this information
            if (Arrays.stream(object.getClass().getInterfaces()).anyMatch(interf -> interf.equals(DescriptionModel.class)))
                result.append("\n").append(((T) object).getDescription());

            for (Field field : FieldUtils.getAllFields(object.getClass())) {
                if (field.isAnnotationPresent(Description.class)) {

                    result.append("\nField ").append(field.getName()).append(" ");
                    if (standardClasses.stream().anyMatch(aClass -> field.getType().equals(aClass))) {
                        // get value of default class
                        result.append("(").append(field.getAnnotation(Description.class).value())
                                .append("): ")
                                .append(getValue(field, object))
                                .append("");
                    } else if (field.getType().equals(List.class)) {
                        // list description
                        result.append(" +list+:\n").append("+++++++++")
                                .append(field.getAnnotation(Description.class).value())
                                .append("+++++++\n");
                        List list = ((List) getValue(field, object));
                        if (list == null || list.isEmpty())
                            result.append("пуст");
                        else {
                            if (standardClasses.stream().anyMatch(o -> o.equals(list.get(0).getClass()))) {
                                for (val item : list)
                                    result.append("\t-").append(item.toString()).append("\n");
                            } else {
                                result.append("\n\t|");
                                for (val item : list)
                                    result.append(getInfo(item).replaceAll("\\n", "\n\t|"));
                            }
                        }
                        result.append("\n++++++++++++++++\n");
                    } else if (!field.getType().isEnum()) {
                        // Field is object
                        result.append(getInfo(getValue(field, object))
                                .replaceAll("\\n", "\n\t"));
                    } else if (field.getType().isEnum()) {
                        // Get all enum fields
                        result.append("Enum (")
                                .append(field.getAnnotation(Description.class).value())
                                .append(") :")
                                .append(FieldUtils
                                        .getField(object.getClass(), field.getName(), true)
                                        .get(object));
                    } else {
                        result.append("None");
                    }
                }
            }
            result.append("\n_____").append(objectName).append("_____\n");
            return result.toString();
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }

    private Object getValue(Field field, Object object) {

        try {
            return FieldUtils.getField(object.getClass(), field.getName(), true).get(object);
        } catch (IllegalAccessException e) {
            throw new Error("(");
        }
    }
}
