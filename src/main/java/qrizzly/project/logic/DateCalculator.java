package qrizzly.project.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ivan on 20.01.2017.
 */
//класс преднозначен для вычисления дат при выборе определенного поля в combo box "Time Period"
public class DateCalculator {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d,yyyy", Locale.ENGLISH);

    //получение даты первого дня прошлого квартала
    public static String getLastQtrStartDate() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int qtr = 3;
        //т.к. в квартале 3 месяца, то отнимаем от текущего тройку
        int lastQtr = month - qtr;
        String qtrStartDate = getQtrStartDate(lastQtr, year);
        return qtrStartDate;
    }
    //получение даты первого дня текущего квартала
    public static String getCurrentQtrStartDate() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        String qtrStartDate = getQtrStartDate(month, year);
        return qtrStartDate;
    }
    // метод в зависимости от месяца и года возвращает
    // дату первого дня в квартале
    private static String getQtrStartDate(int month, int year){
        if (month < 0) return "Oct 1," + (year - 1);
        if (month >= 0 && month < 3) return "Jan 1," + year;
        if (month >= 3 && month < 6) return "Apr 1," + year;
        if (month >= 6 && month < 9) return "Jul 1," + year;
        else return "Oct 1," + year;
    }

    // получение последнего дня в прошлом квартале
    public static String getLastQtrEndDate() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int qtr = 3;
        //отнимаем от текущего месяца тройку, чтобы перейти в прошлый квартал
        int lastQtr = month - qtr;
        int lastMonthOfQtrNumber = 0;
        if (lastQtr < 0) {
            //last year dec
            lastMonthOfQtrNumber = -1;
        }
        if (lastQtr >= 0 && lastQtr < 3) {
            //mar
            lastMonthOfQtrNumber = 2;
        }
        if (lastQtr >= 3 && lastQtr < 6) {
            //Jun
            lastMonthOfQtrNumber =5;
        }
        if (lastQtr >= 6 && lastQtr < 9) {
            //Sep
            lastMonthOfQtrNumber = 8;
        }
        calendar.set(year, lastMonthOfQtrNumber, 1);
        // данный метод устанавливает дату последнего дня в месяце
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTime());
    }
    // получение даты начала прошлого месяца
    public static String getLastMonthStart() {
        // в связи с тем что многие методы отличались друг от друга
        // лишь вычитанием единицы от текущего года/месяца
        // было решено ввести параметр, чтобы избежать дублирования кода
        Calendar calendar = getMonthStartDate(-1);
        return dateFormat.format(calendar.getTime());
    }
    // получение даты окончания прошлого месяца
    public static String getLastMonthEnd() {
        Calendar calendar = getMonthStartDate(-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTime());
    }
    // получение даты начала текущего месяца
    public static String getCurrentMonthStart(){
        Calendar calendar = getMonthStartDate(0);
        return dateFormat.format(calendar.getTime());
    }
    // метод возвращает первый день месяца в зависимости от параметра
    private static Calendar getMonthStartDate(int param) {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = currentMonth + param;
        calendar.set(year, month, 1);
        return calendar;
    }

    // получение текущей даты
    public static String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    // получение даты начала года в зависимости от параметра
    private static Calendar getYearStartDate(int param){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year+param, 0, 1);
        return calendar;
    }
    // получени даты начала текущего года
    public static String getCurrentYearStartDate(){
        Calendar calendar = getYearStartDate(0);
        return dateFormat.format(calendar.getTime());
    }
    // получение даты начала прошлого года
    public static String getLastYearStartDate(){
        Calendar calendar = getYearStartDate(-1);
        return dateFormat.format(calendar.getTime());
    }
    // получение даты окончания прошлого года
    public static String getLastYearEndDate(){
        Calendar calendar = getYearStartDate(-1);
        int lastYear = calendar.get(Calendar.YEAR);
        int dec = 11;
        calendar.set(lastYear, dec, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTime());
    }

}
