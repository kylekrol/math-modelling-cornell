close all;

fprintf(['\n' 'Miles per Gallon Data' '\n'])
calcmpgstats('mpg/rt10mpg.txt')
calcmpgstats('mpg/rt11mpg.txt')
calcmpgstats('mpg/rt15mpg.txt')
calcmpgstats('mpg/rt17mpg.txt')
calcmpgstats('mpg/rt81mpg.txt')
calcmpgstats('mpg/rt82mpg.txt')
fprintf(['\n' 'Gallons per Route Data' '\n'])
calcgasstats('gas/rt10gas.txt')
calcgasstats('gas/rt11gas.txt')
calcgasstats('gas/rt15gas.txt')
calcgasstats('gas/rt17gas.txt')
calcgasstats('gas/rt81gas.txt')
calcgasstats('gas/rt82gas.txt')

function calcmpgstats(file)

    data = csvread(file);
    rootn = sqrt(length(data(:,1)));
    fprintf(['\n' file '\n'])
    fprintf('Gas    - mean=%0.3f unc=%0.3f\n',mean(data(:,1)),std(data(:,1))/rootn)
    fprintf('Hybrid - mean=%0.3f unc=%0.3f\n',mean(data(:,2)),std(data(:,2))/rootn)
    
end

function calcgasstats(file)

    data = csvread(file);
    rootn = sqrt(length(data(:,1)));
    ddata = data(:,1) - data(:,2);
    fprintf(['\n' file '\n'])
    fprintf('Gas    - mean=%0.3f unc=%0.3f\n',mean(data(:,1)),std(data(:,1))/rootn)
    fprintf('Hybrid - mean=%0.3f unc=%0.3f\n',mean(data(:,2)),std(data(:,2))/rootn)
    fprintf('Delta  - mean=%0.3f unc=%0.3f\n',mean(ddata),std(ddata)/rootn)

end