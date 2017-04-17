package edu.wctc.phd.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

/**
* Utility class for the purpose of managing LocalDateTimes and LocalDates.
* <P>
* Change History:
* <UL>
* <LI>2017-04-17 - initial version.</LI>
* </UL>
* 
* <P>
* MIT License
* <P>
* Copyright (c) 2017 Peter Davis
* <P>
* Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
* <P>
* The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
* <P>
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
* <P>
 * @author Peter Davis
 */
public class DateUtilities {
    
    /**
     * Format a <code>LocalDateTime</code> according to the date pattern "yyyy-MM-dd HH:mm:ss"
     *
     * @param dateTime - a <code>LocalDateTime</code> object
     * @return a string formatted according to the default date pattern
     * @throws IllegalArgumentException if dateTime is null
     */
    public String toString(LocalDateTime dateTime) throws IllegalArgumentException {
        if (dateTime == null) {
            throw new IllegalArgumentException("Error: dateTime argument cannot be null");
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(dateTime);
    }
    
    /**
     * Format a <code>LocalDateTime</code> according to the desired string pattern
     *
     * @param dateTime - a <code>LocalDateTime</code> object
     * @param pattern - a desired string pattern for the dateTime
     * 
     * @return a string formatted according to the desired string pattern
     * @throws IllegalArgumentException if dateTime is null or if pattern is not recognized
     */
    public String toString(LocalDateTime dateTime, String pattern) throws IllegalArgumentException {
        if (dateTime == null) {
            throw new IllegalArgumentException("Error: dateTime argument cannot be null");
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return df.format(dateTime);
    }
    
    /**
     * Format a <code>LocalDateTime</code> according to the <code>dateTimeStyle</code> pattern
     *
     * @param dateTime - a <code>LocalDateTime</code> object
     * @param dateTimeStyle - a desired style for the dateTime
     * 
     * @return a string formatted according to the <code>dateTimeStyle</code> pattern
     * @throws IllegalArgumentException if dateTime is null or dateTimeStyle is null
     */
    public String toString(LocalDateTime dateTime, FormatStyle dateTimeStyle) throws IllegalArgumentException {
        if (dateTime == null) {
            throw new IllegalArgumentException("Error: dateTime argument cannot be null");
        }
        if (dateTimeStyle == null) {
            throw new IllegalArgumentException("Error: dateTimeStyle argument cannot be null");
        }
        DateTimeFormatter df = DateTimeFormatter.ofLocalizedDateTime(dateTimeStyle);
        return df.format(dateTime);
    }
    
    /**
     * Attempts to convert a String representation of a <code>LocalDateTime</code> of format "yyyy-MM-dd HH:mm:ss"
     * to a java.time.LocalDateTime
     * object.
     *
     * @param dateTimeString - a string representation of a date and time
     * @return a java.time.LocalDateTime object
     * @throws IllegalArgumentException if the date string cannot be parsed.
     */
    public LocalDateTime toDate(String dateTimeString)
            throws IllegalArgumentException {
        LocalDateTime dateTime = null;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            dateTime = LocalDateTime.parse(dateTimeString, df);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dateTime;
    }
    
    /**
     * Attempts to convert a String representation of a <code>LocalDateTime</code> of a given format
     * to a java.time.LocalDateTime
     * object.
     *
     * @param dateTimeString - a string representation of a date and time
     * @param formatter - a <code>DateTimeFormatter</code> object that indicates the format of the string
     * @return a java.time.LocalDateTime object
     * @throws IllegalArgumentException if the date string cannot be parsed.
     */
    public LocalDateTime toDate(String dateTimeString, DateTimeFormatter formatter)
            throws IllegalArgumentException {
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return dateTime;
    }
    
    /**
     * Returns the difference between two LocalDateTime objects in days, hours, minutes, or seconds
     * 
     * @param startDate - a <code>LocalDateTime</code> object
     * @param endDate - a <code>LocalDateTime</code> object
     * @param dateUnit - a <code>ChronoUnit</code> object that specifies days, hours, minutes, or seconds
     * @return an integer based on the given ChronoUnit
     */
    public int dateDiff(LocalDateTime startDate, LocalDateTime endDate, ChronoUnit dateUnit){
        Duration diff = Duration.between(startDate, endDate);
        
        int value;

        switch (dateUnit) {
            case DAYS:
                value = (int) diff.toDays();
                break;

            case HOURS:
                value = (int) diff.toHours();
                break;

            case MINUTES:
                value = (int) diff.toMinutes();
                break;

            case SECONDS:
                value = (int) (diff.toMillis() / 1000L);
                break;

            default:
                value = (int) diff.toHours();
        }

        return value;
    }
    
    /**
     * Gets a LocalDateTime from a separate LocalDate and LocalTime
     * 
     * @param date - a <code>LocalDate</code> object
     * @param time - a <code>LocalTime</code> object
     * @return -  a <code>LocalDateTime</code> object
     * @throws IllegalArgumentException if the date or time argument is null.
     */
    public LocalDateTime getLocalDateTime(LocalDate date, LocalTime time)
            throws IllegalArgumentException {
        if(date == null || time == null){
            throw new IllegalArgumentException("Error: date or time argument cannot be null");
        }
        return LocalDateTime.of(date, time);
    }
    
    /**
     * Gets a LocalDate from a LocalDateTime object
     * 
     * @param dateTime - a <code>LocalDateTime</code> object
     * @return - a <code>LocalDate</code> object
     * @throws IllegalArgumentException if the dateTime argument is null.
     */
    public LocalDate getLocalDate(LocalDateTime dateTime)
            throws IllegalArgumentException {
        if(dateTime == null){
            throw new IllegalArgumentException("Error: dateTime argument cannot be null");
        }
        return LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth());
    }
}
