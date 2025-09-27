@echo off
cd /d "d:\MANULA\SLIIT\Y3S1\Advanced Software Engineering\Assignment\Assignemnt\student-app"
echo Merge remote changes > temp_merge_msg.txt
git commit -F temp_merge_msg.txt
del temp_merge_msg.txt
git push origin development