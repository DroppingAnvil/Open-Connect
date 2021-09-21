package dev.droppinganvil.v2.remotedirectory.io;

import java.io.InputStream;
import java.io.OutputStream;

public class IOJob {
    public InputStream is;
    public OutputStream os;
    public JobType jt;
    /**
     * Represents the success of the individual IO operation this job should undertake, will not become true on root object until all jobs and doAfter s have completed
     */
    public boolean success;
    /**
     * Use when multiple IO operations are required for one operation.
     * Will be null unless set
     */
    public IOJob next;

    public IOJob(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
        this.jt = JobType.WRITE;
    }

    public IOJob(InputStream is) {
        this.is = is;
        this.jt = JobType.REVERSE;
    }

    /**
     * This method will be fired after an IO operation on an IOJob unless it has a next IOJob, in this case it will be fired after all jobs contained are completed.
     * Fires on completion of all IO operations in an IOJob in their respective order
     * @param success true unless an exception has occurred
     */
    public void doAfter(boolean success) {

    }
}
