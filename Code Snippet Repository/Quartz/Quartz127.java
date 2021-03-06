    public void notifySchedulerListenersPausedJobs(String group) {
        // build a list of all scheduler listeners that are to be notified...
        List<SchedulerListener> schedListeners = buildSchedulerListenerList();

        // notify all scheduler listeners
        for(SchedulerListener sl: schedListeners) {
            try {
                sl.jobsPaused(group);
            } catch (Exception e) {
                getLog().error(
                        "Error while notifying SchedulerListener of paused job group: "
                                + group, e);
            }
        }
    }
