package com.dheeraj.learning.labwatcher.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class JenkinsClient {
    public static void main(String[] args) throws Exception{
        JenkinsClient jenkinsClient = new JenkinsClient();
        JenkinsServer jenkins = new JenkinsServer(new URI("http://eng-colliders-jenkins:8090"), "gopad", "971422b5bc3e9fde86130e93fae8095e");

        jenkinsClient.showNumberOfBuildsInQueue(jenkins);
    }

    public void showNumberOfBuildsInQueue(JenkinsServer jenkinsServer) throws Exception {
        Job job = jenkinsServer.getJob("PerfTestProd");
        Map<String, Computer> map = jenkinsServer.getComputers();
        List<Executor> list = map.get("master").details().getExecutors();
        System.out.println(list.size());

        List<Build> builds = ((JobWithDetails) job).getAllBuilds();
        JobWithDetails jobWithDetails = job.details();
        Queue queue = jenkinsServer.getQueue();
        System.out.println(queue.getItems().size());
        //System.out.println(builds.size());
    }
}
