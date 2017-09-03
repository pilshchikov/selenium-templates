package com.templates.utils;

import com.templates.annotations.Description;
import com.templates.enums.AttachmentFormat;
import lombok.Getter;
import lombok.experimental.Accessors;
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

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final Attachments attachments = new Attachments();

    /**
     * Безусловное ожидание в течение заданного времени
     *
     * @param millis - время ожидания в миллисекундах
     */
    public void sleep(int millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Ожидание в течении заданного времени
     *
     * @param seconds число в секундах сколько необходимо ожидать
     */
    public void sleeps(int seconds) {
        sleep(seconds * 1000);
    }

    /**
     * Ожидаем условие, если не выполняется, то ждем до момента пока условие не выполнится или не
     * кончится количество повторений
     *
     * @param preCondition       условие, которое выполняется перед ожиданием. Должно возвращаать true, чтобы
     *                           выйти из ожидания. Пример: exampleString.equal(getPage().mainElement.getText())
     * @param action             действие, которое выполняется после ожидания. Пример: () -> getPage().refresh()
     * @param iterCycles         количество повторений preCondition
     * @param waitCycleInSeconds время между выполнением preCondition в секундах
     * @return статус выполнения условия
     */
    public Boolean waitFor(Callable<Boolean> preCondition, Runnable action, Integer iterCycles,
                           Integer waitCycleInSeconds) {

        try {
            Integer iter = 0;
            while (iter < iterCycles && !preCondition.call()) {
                iter++;
                action.run();
                sleeps(waitCycleInSeconds);
            }
            return iter < iterCycles;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new Error("При выполнении условия выпала ошибка: " + e.getMessage());
        }
    }

    /**
     * Ожидаем условие, если не выполняется, то ждем до момента пока условие не выполнится или не
     * кончится количество повторений
     *
     * @param preCondition       условие, которое выполняется перед ожиданием. Должно возвращаать false, чтобы
     *                           выйти из ожидания Пример: exampleString.equal(getPage().mainElement.getText())
     * @param iterCycles         количество повторений preCondition
     * @param waitCycleInSeconds время между выполнением preCondition в секундах
     * @return статус выполнения условия
     */
    public Boolean waitFor(Callable<Boolean> preCondition, Integer iterCycles, Integer waitCycleInSeconds) {

        return waitFor(preCondition, () -> toString(), iterCycles, waitCycleInSeconds);
    }

    /**
     * Ожидаем пока выполнится условие
     *
     * @param preCondition  условие, которое взаимодействует с экземпляром класса, чтобы выдать статус готов
     *                      ли объект или искать новый экземпляр Пример: (searchedUser) ->
     *                      validUser(searchedUser) - определяем отвечает ли юзер каким-либо требоваиям
     * @param postCondition получение экземпляра класса для проведения preCondition
     * @param iterCycles    количество итераций
     * @param waitInSeconds количество секунд между циклами
     * @return найденный экземпляр класса
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
     * Прикрепить файл/текст к отчету
     *
     * @param format         формат файла/текста
     * @param attachment     прикрепляемый объект
     * @param attachmentName название аттачмента в отчете
     */
    public void takeAttachment(AttachmentFormat format, Object attachment, String attachmentName) {

        attachments().takeAttachment(format, attachment, attachmentName);
    }

    /**
     * Получаем описание объекта
     * <p>
     * Берем поля с аннотацией @Description("название элемента") достаем значение поля, достаем
     * название элемента и пихаем с String формат для того, чтобы приложить в отчет
     *
     * @param object объект
     */
    public String getInfo(Object object) {

        if (object == null)
            return "null\n";
        try {
            // Список нативных классов, чтобы отличать от наших кастомных
            final List<Class> standardClasses = Arrays.asList(String.class, Boolean.class,
                    Integer.class, Long.class, Float.class, Date.class);
            String objectName = "";
            StringBuilder result = new StringBuilder("");
            // берем аннотацию на классе
            if (object.getClass().isAnnotationPresent(Description.class)) {
                objectName = object.getClass().getAnnotation(Description.class).value();
                result = result.append("\n_____| ").append(objectName).append("|_____");
            }
            // идем по полям
            for (Field field : FieldUtils.getAllFields(object.getClass())) {
                if (field.isAnnotationPresent(Description.class)) {

                    // объявляем новое найденное поле
                    result.append("\nПоле ").append(field.getName()).append(" ");
                    if (standardClasses.stream()
                            .anyMatch(aClass -> field.getType().equals(aClass))) {
                        // если поле является станжартным классом то берем значение
                        result.append("(").append(field.getAnnotation(Description.class).value())
                                .append("): ")
                                .append(getValue(field, object))
                                .append("");
                    } else if (field.getType().equals(List.class)) {
                        // если список объектов то заходим в объект (если он не стандартный) и
                        // описываем его элементы
                        result.append(" +список+:\n").append("+++++++++")
                                .append(field.getAnnotation(Description.class).value())
                                .append("+++++++\n");
                        List list = ((List) getValue(field, object));
                        if (list == null || list.size() == 0)
                            result.append("пуст");
                        else {
                            if (standardClasses.stream()
                                    .anyMatch(o -> o.equals(list.get(0).getClass()))) {
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
                        // если объект не стандартный и не является енамом то заходим внутрь и
                        // описываем все поля
                        result.append(getInfo(getValue(field, object))
                                .replaceAll("\\n", "\n\t"));
                    } else if (field.getType().isEnum()) {
                        // если константа то просто берем значение
                        result.append("Енам (")
                                .append(field.getAnnotation(Description.class).value())
                                .append(") :")
                                .append(FieldUtils
                                        .getField(object.getClass(), field.getName(), true)
                                        .get(object));
                    } else {
                        result.append("не знаем что за поле");
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
