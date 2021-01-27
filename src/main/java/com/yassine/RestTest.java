package com.yassine;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yassine.dao.CompteRepository;
import com.yassine.entities.Compte;

@RestController
public class RestTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Autowired
	private CompteRepository repo;

	@GetMapping("/job")
	public BatchStatus load() throws Exception {
		Map<String, JobParameter> params = new HashMap<>();
		params.put("time", new JobParameter(System.currentTimeMillis()));

		JobExecution jobExecution = jobLauncher.run(job, new JobParameters(params));

		while (jobExecution.isRunning()) {
		}

		return jobExecution.getStatus();
	}

	@GetMapping("/comptes")
	public List<Compte> comptes() {
		return repo.findAll();
	}
}