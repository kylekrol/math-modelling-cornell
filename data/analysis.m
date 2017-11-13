close all;

calcmpgstats('mpg/rt10mpg.txt')
calcmpgstats('mpg/rt11mpg.txt')
calcmpgstats('mpg/rt15mpg.txt')
calcmpgstats('mpg/rt17mpg.txt')
calcmpgstats('mpg/rt81mpg.txt')
calcmpgstats('mpg/rt82mpg.txt')

function calcmpgstats(file)

    data = csvread(file);
    fprintf(['\n' file '\n'])
    fprintf('Gas    - mean=%0.3f std=%0.3f\n',mean(data(:,1)),std(data(:,1)))
    fprintf('Hybrid - mean=%0.3f std=%0.3f\n',mean(data(:,2)),std(data(:,2)))
    
end