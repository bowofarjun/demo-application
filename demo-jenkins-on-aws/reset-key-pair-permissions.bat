Icacls demo-jenkins-key-pair.pem /c /t /Inheritance:d
Icacls demo-jenkins-key-pair.pem /c /t /Grant %UserName%:F
TakeOwn /F demo-jenkins-key-pair.pem
Icacls demo-jenkins-key-pair.pem /c /t /Grant:r %UserName%:F
Icacls demo-jenkins-key-pair.pem /c /t /Remove:g "Authenticated Users" BUILTIN\Administrators BUILTIN Everyone System Users