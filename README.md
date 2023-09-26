[![CircleCI](https://dl.circleci.com/status-badge/img/gh/jpickup/udacity-capstone/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/jpickup/udacity-capstone/tree/main)

## Project Overview
This application is a web-based route repository for outdoor activities such as running, hiking or cycling. The data
is stored in DynamoDB, AWS's NoSQL DB, and is accessed via a Java backend built using Springboot. The frontend is a Vue.js application that makes REST calls into the Java backend. 

### AWS setup
The backend runs in a Kubernetes cluster, which is setup using Clouformation templates.

The frontend is hosted in an S3 bucket fronted by a CloudFront CDN.

Storage is in three DynamoDB tables for the routes, route ratings and users.

CircleCI jobs create the infrastructure, compile, test and lint the code and, if successful perform a rolling deployment onto the AWS infrastructure.


### Important files and directories in this repo
| File               | Description                                                                |
| ------------------ | -------------------------------------------------------------------------- |
| aws                | Scripts for setup of objects in AWS                                        |
| aws/cloudformation | Cloudformation templates for the AWS resources                             |
| aws/kubernetes     | Kubernetes scripts                                                         |
| backend            | The Java backend, built using maven                                        |
| backend/domain     | XSDs for GPX, used by JAXB to generate Java classes                        |
| backend/business   | The springboot application                                                 |
| frontend           | The Vue.js frontend, built using npm                                       |

### CircleCI Pipeline
The following jobs run in the CircleCI pipline:
1. `setup-backend` - extract AWS credentials needed for later jobs
2. `deploy-infrastructure` - deploy AWS infrastructure defined in CloudFormation
3. `build-backend` - build the Java backend using MAven
4. `lint-backend` - scan the Java backend using CheckStyle
4. `deploy-backend` - build the Docker image for the backend and upload to ECR
5. `generate-frontend-config` - generate config file for the frontend with the backend connection details
6. `build-frontend` - build the frontend using NPM
7. `lint-frontend` - lint the frontend using NPM
8. `scan-frontend` - scan the frontend using NPM
9. `deploy-frontend` - deploy the frontend build to an S3 bucket
