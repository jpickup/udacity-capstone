[![CircleCI](https://dl.circleci.com/status-badge/img/gh/jpickup/udacity-capstone/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/jpickup/udacity-capstone/tree/main)

## Project Overview
This application is a web-based route repository for outdoor activities such as running, hiking or cycling. The data
is stored in DynamoDB, AWS's NoSQL DB, and is accessed via a Java backend built using Springboot. The frontend is a Vue.js application that makes REST calls into the Java backend. 

### AWS setup
The backend runs in a Kubernetes cluster, which is setup using Clouformation templates.

The frontend is hosted in an S3 bucket fronted by a CloudFront CDN.

Storage is in three DynamoDB tables for the routes, route ratings and users.

CircleCI jobs create the infrastructure, compile, test and lint the code and, if successful perform a rolling deployment onto the AWS infrastructure.


### Files in this repo
| File               | Description                                                                |
| ------------------ | -------------------------------------------------------------------------- |
| README.md          | This file                                                                  |
| aws                | Scripts for cloud 
| aws/cloudformation | Cloudformation templates for the AWS resources                             |
| backend            | The Java backend, built using maven                                        |
| backend/domain     | XSDs for GPX, used by JAXB to generate Java classes                        |
| backend/business   | The springboot application                                                 |
| frontend           | The Vue.js frontend, built using npm                                       |