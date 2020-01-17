package root.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import root.demo.entites.Magazine;
import root.demo.entites.ScienceField;
import root.demo.entites.UserDetails;
import root.demo.model.FormFieldsDto;
import root.demo.model.FormSubmissionDto;
import root.demo.model.TaskDto;
import root.demo.services.MagazineService;
import root.demo.services.ScienceFieldService;
import root.demo.services.UserDetailsService;

@Controller
@RequestMapping("/magazine")
public class MagazineController {

	@Autowired
	IdentityService identityService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private MagazineService magazineService;

	@Autowired
	TaskService taskService;

	@Autowired
	FormService formService;

	@Autowired
	ScienceFieldService scienceFieldService;
	
	@Autowired
	UserDetailsService userDetailsService;

	private String processInstanceId;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	@GetMapping(path = "/get", produces = "application/json")
	public @ResponseBody FormFieldsDto get() {

		ScienceField sff = new ScienceField();
		sff.setName("math");
		ScienceField f = new ScienceField();
		f.setName("biology");
		
		scienceFieldService.createScienceField(sff);

		scienceFieldService.createScienceField(f);

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("creatingMagazine");
		this.setProcessInstanceId(pi.getId());

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);

		List<ScienceField> sfields = scienceFieldService.getAll();

		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for (FormField fp : properties) {
		

			if (fp.getId().equals("scienceField")) {
		
				Map<String, String> values = (Map<String, String>) fp.getType().getInformation("values");
				for (ScienceField sf : sfields) {
					values.put(sf.getScienceFieldId().toString(), sf.getName());
				}
			}

			System.out.println(fp.getId() + fp.getType());
		}

		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}

	@GetMapping(path = "/get/admin", produces = "application/json")
	public @ResponseBody FormFieldsDto getAdminTask() {

		Task task = taskService.createTaskQuery().processInstanceId(this.getProcessInstanceId()).list().get(0);

		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for (FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}

		return new FormFieldsDto(task.getId(), this.getProcessInstanceId(), properties);
	}

	@PostMapping(path = "/post/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		// list all running/unsuspended instances of the process
//		    ProcessInstance processInstance =
//		        runtimeService.createProcessInstanceQuery()
//		            .processDefinitionKey("Process_1")
//		            .active() // we only want the unsuspended process instances
//		            .list().get(0);

//			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "magazine", dto);
		System.out.println(dto);

		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/post/editorsReviewers/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity postEV(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "editorsAndReviewers", dto);

		formService.submitTaskForm(taskId, map);
		System.out.println("posting editors and reviewers");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/post/admin/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity postAdminDecision(@RequestBody List<FormSubmissionDto> dto,
			@PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "adminDecision", dto);

		formService.submitTaskForm(taskId, map);
		System.out.println("posting admin decision");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (FormSubmissionDto temp : list) {
			map.put(temp.getFieldId(), temp.getFieldValue());
		}

		return map;
	}

	@GetMapping(path = "/get/tasks/{processInstanceId}", produces = "application/json")
	public @ResponseBody FormFieldsDto get(@PathVariable String processInstanceId) {

		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();

		TaskDto t = new TaskDto(tasks.get(0).getId(), tasks.get(0).getName(), tasks.get(0).getAssignee());
		dtos.add(t);

		TaskFormData tfd = formService.getTaskFormData(tasks.get(0).getId());
		ArrayList<UserDetails> userReviewers = userDetailsService.getAllReviewers();
		List<FormField> properties = tfd.getFormFields();
		for (FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
			
//			if(fp.getId().equals("editorA")) {
//				Map<String, String> values = (Map<String, String>) fp.getType().getInformation("values");	
//				for (UserDetails ud : userReviewers) {
//					values.put(ud.getlName(), ud.getlName());
//				}
//			}

		}

		return new FormFieldsDto(tasks.get(0).getId(), this.getProcessInstanceId(), properties);
	}

}
