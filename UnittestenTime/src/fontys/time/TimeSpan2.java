/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author Robin
 */
public class TimeSpan2 implements ITimeSpan
{

    /* class invariant: 
     * A stretch of time with a begin time and end time.
     * The end time is always later then the begin time; the length of the time span is
     * always positive
     * 
     */
    private ITime bt;
    private long dur; // Duration of the TimeSpan in minutes
    
    /**
     * 
     * @param bt must be earlier than et
     * @param et 
     */
    public TimeSpan2(ITime bt, ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        this.dur = et.difference(bt);
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        return bt.plus(Math.round(dur));
    }

    @Override
    public int length() {
        return Math.round(dur);
    }

    @Override
    public void setBeginTime(ITime beginTime) {
//        if (beginTime.compareTo(et) >= 0) {
//            throw new IllegalArgumentException("begin time "
//                    + bt + " must be earlier than end time " + et);
//        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) <= 0) {
            throw new IllegalArgumentException("end time "
                    + endTime + " must be later then begin time " + bt);
        }

        dur = endTime.difference(bt);
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }
        
        dur += minutes;
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || this.getEndTime().compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }
        
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (this.getEndTime().compareTo(timeSpan.getEndTime()) > 0) {
            endtime = this.getEndTime();
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan(begintime, endtime);

    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan) {
        //Aangepast door Mario
        //Het object bt is groter dan timeSpan. dan krijg je true 
        //terug. Als de beide opjecten hetzelfde zijn of timeSpan groter is dan krijg je false.
        //Dus zal dit veranderd moeten worden naar <
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }
        //verandert naar >
        if (this.getEndTime().compareTo(timeSpan.getEndTime()) > 0) {
            endtime = this.getEndTime();
        } else {
            endtime = timeSpan.getEndTime();
        }
        //verandert naar <=   
        if (begintime.compareTo(endtime) <= 0) {
            return null;
        }

        return new TimeSpan(begintime, endtime);
    }
}