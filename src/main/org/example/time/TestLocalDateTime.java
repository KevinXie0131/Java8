package org.example.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class TestLocalDateTime {
    //1. LocalDate、LocalTime、LocalDateTime
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ld2 = LocalDateTime.of(2016, 11, 21, 10, 10, 10);
        System.out.println(ld2);

        LocalDateTime ldt3 = ld2.plusYears(20);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ld2.minusMonths(2);
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());
    }

    //2. Instant - 1970年1月1日 00:00:00
    @Test
    public void test2(){
        Instant ins = Instant.now();
        System.out.println(ins);

        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        System.out.println(ins.getNano());

        Instant ins2 = Instant.ofEpochSecond(5);
        System.out.println(ins2);
    }

    //3.
    //Duration : time
    //Period : date
    @Test
    public void test3(){
        Instant ins1 = Instant.now();

        System.out.println("--------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Instant ins2 = Instant.now();

        System.out.println("time elapse：" + Duration.between(ins1, ins2));
        System.out.println("----------------------------------");

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2011, 1, 1);

        Period pe = Period.between(ld2, ld1);
        System.out.println("time elapse：" + pe);
        System.out.println(pe.getYears());
        System.out.println(pe.getMonths());
        System.out.println(pe.getDays());
    }

    //4. TemporalAdjuster
    @Test
    public void test4(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else{
                return ldt4.plusDays(1);
            }
        });

        System.out.println(ldt5);

    }

    //5. DateTimeFormatter
    @Test
    public void test5(){
	//DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        LocalDateTime newLdt = ldt.parse(strDate, dtf);
        System.out.println(newLdt);
    }

    @Test
    public void test6(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    //6.ZonedDate、ZonedTime、ZonedDateTime
    @Test
    public void test7(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        LocalDateTime ldt1 = LocalDateTime.now(ZoneId.of("America/Toronto"));
        System.out.println(ldt1);

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zdt);
    }

}
