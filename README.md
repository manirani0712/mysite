# Sample AEM project template

Template Type -
/conf/mysite/settings/wcm/template-types/mytemplate
CUSTOM TEMPLATE TYPE

Template -
/conf/mysite/settings/wcm/templates/my-custom-template
my-custom-template

ClientLibs - 
/apps/mysite/clientlibs/clientlib-custom

Experience Fragment - 
/content/experience-fragments/mysite/us/en/site/custom-experience-fragment

Student Info Simple Component - 
/apps/mysite/components/student-info-simple

Student Info Multifield Component - 
/apps/mysite/components/student-info-multi

User/Groups -
UserGroups.java
/apps/mysite/components/usergroups

Modify the property - servlet(ResourceType)
RecentArticleServlet.java
http://localhost:4502/content/mysite/jcr:content.recent.json

Query the Assets- 
QueryBuilderServlet.java
http://localhost:4502/bin/aemtask/querybuilder

Simple Scheduler - 
SimpleScheduledTask.java
http://localhost:4502/system/console/slinglog/tailer.txt?tail=10000&grep=*&name=%2Flogs%2Fsimplescheduler.log

Custom Scheduler - 
CustomScheduler.java
http://localhost:4502/system/console/slinglog/tailer.txt?tail=10000&grep=*&name=%2Flogs%2FCustomScheduler.log

OSGI Configs - 
/apps/mysite/components/osgiconfigs
OSGIConfigImpl.java





