# Information on extending/integrating with Pipeline Model Definition

## API Endpoints

All API endpoints are exposed under `JENKINS_URL/pipeline-model-converter` currently.

### JSON Schema
* *URL*: `JENKINS_URL/pipeline-model-converter/schema`
* *Parameters*: None
* *Info*: JSON schema for the JSON representation of the model.
* *Returns*: The JSON schema

### Validation of `Jenkinsfile`
* *URL*: `JENKINS_URL/pipeine-model-converter/validateJenkinsfile`
* *Parameters*: `jenkinsfile` - the `Jenkinsfile` contents
* *Info*: Takes a `Jenkinsfile` and validates its syntax, semantics, steps, parameters etc on the Jenkins master.
* *Returns*: JSON with a `result` field that will either be `success` or `failure`. If `failure`, there'll be an additional array in the `errors` field of the error messages encountered.

### Validation of JSON representation
* *URL*: `JENKINS_URL/pipeine-model-converter/validateJson`
* *Parameters*: `json` - the JSON representation
* *Info*: Takes a JSON representation of the model and validates its syntax, semantics, steps, parameters etc on the Jenkins master.
* *Returns*: JSON with a `result` field that will either be `success` or `failure`. If `failure`, there'll be an additional array in the `errors` field of the error messages encountered.

### Conversion to JSON representation from Jenkinsfile
* *URL*: `JENKINS_URL/pipeline-model-converter/toJson`
* *Parameters*: `jenkinsfile` - the `Jenkinsfile` contents
* *Info*: Takes a `Jenkinsfile` and converts it to the JSON representation for its `pipeline` step.
* *Returns*: JSON with a `result` field that will either be `success` or `failure`. If `success`, the JSON representation will be in the `json` field. If `failure`, there'll be an additional array in the `errors` field of the error messages encountered.

### Conversion to Jenkinsfile from JSON representation
* *URL*: `JENKINS_URL/pipeline-model-converter/toJenkinsfile`
* *Parameters*: `json` - the JSON representation of the model
* *Info*: Takes the JSON representation of the model and converts it to the contents for a `Jenkinsfile` invoking the `pipeline` step.
* *Returns*: JSON with a `result` field that will either be `success` or `failure`. If `success`, the `Jenkinsfile` contents will be in the `jenkinsfile` field. If `failure`, there'll be an additional array in the `errors` field of the error messages encountered.

## Java API

### JSON schema
* *API call*: `org.jenkinsci.plugins.pipeline.parser.Converter.getJSONSchema()`
* *Parameters*: None
* *Returns*: Returns the JSON representation schema

### `WorkflowRun` execution model
* *API call*: `someWorkflowRun.getAction(ExecutionModelAction.class).getStages()`
* *Parameters*: None
* *Returns*: The `ModelASTStages` for a particular `WorkflowRun`. This is generated at the beginning of build execution, stripped of 
`sourceLocation` references for serialization purposes, and attached to the `WorkflowRun`. Stage and branch execution order can be found here, 
even when the build hasn't gotten to those stages or branches yet, since the model dictates execution order.

