package ch.ipt.thread;

public abstract class AbstractGenericThread extends Thread {


    @Override
    public void run() {
        setup();
        try {
            while (checkConfig()) {
                checkInternalState();
                internalRun();            }
        } catch (Exception e) {
            e.printStackTrace();
            cleanUp();
        }

    }

    abstract protected void setup();
    abstract protected boolean checkConfig();
    abstract protected void checkInternalState();
    abstract protected void internalRun();
    abstract protected void cleanUp();
}
