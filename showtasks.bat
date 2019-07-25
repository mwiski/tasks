call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openinchrome
echo.
echo RUNCRUD.BAT has errors - breaking work
goto fail

:openinchrome
call start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open website
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.