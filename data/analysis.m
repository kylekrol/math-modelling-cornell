close all;

calcmpgstats('rt10mpg.txt')
calcmpgstats('rt11mpg.txt')
calcmpgstats('rt15mpg.txt')
calcmpgstats('rt17mpg.txt')
calcmpgstats('rt81mpg.txt')
calcmpgstats('rt82mpg.txt')

function calcmpgstats(file)

    data = csvread(file);
    fprintf(['\n' file '\n'])
    fprintf('Gas    - mean=%0.3f std=%0.3f\n',mean(data(:,1)),std(data(:,1)))
    fprintf('Hybrid - mean=%0.3f std=%0.3f\n',mean(data(:,2)),std(data(:,2)))
    
end