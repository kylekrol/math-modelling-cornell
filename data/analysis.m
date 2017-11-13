close all;

fprintf(['\n' 'Miles per Gallon Data (Summer)' '\n'])
calcmpgstats('mpg/rt10summpg.txt')
calcmpgstats('mpg/rt11summpg.txt')
calcmpgstats('mpg/rt15summpg.txt')
calcmpgstats('mpg/rt17summpg.txt')
calcmpgstats('mpg/rt81summpg.txt')
calcmpgstats('mpg/rt82summpg.txt')
fprintf(['\n' 'Gallons per Route Data (Summer)' '\n'])
calcgasstats('gas/rt10sumgas.txt')
calcgasstats('gas/rt11sumgas.txt')
calcgasstats('gas/rt15sumgas.txt')
calcgasstats('gas/rt17sumgas.txt')
calcgasstats('gas/rt81sumgas.txt')
calcgasstats('gas/rt82sumgas.txt')

function calcmpgstats(file)

    data = csvread(file);
    rootn = sqrt(length(data(:,1)));
    fprintf(['\n' file '\n'])
    fprintf('Gas    - mean=%0.3f unc=%0.4f\n',mean(data(:,1)),std(data(:,1))/rootn)
    fprintf('Hybrid - mean=%0.3f unc=%0.4f\n',mean(data(:,2)),std(data(:,2))/rootn)
    
end

function calcgasstats(file)

    data = csvread(file);
    rootn = sqrt(length(data(:,1)));
    ddata = data(:,1) - data(:,2);
    fprintf(['\n' file '\n'])
    fprintf('Gas    - mean=%0.3f unc=%0.4f\n',mean(data(:,1)),std(data(:,1))/rootn)
    fprintf('Hybrid - mean=%0.3f unc=%0.4f\n',mean(data(:,2)),std(data(:,2))/rootn)
    fprintf('Delta  - mean=%0.3f unc=%0.4f\n',mean(ddata),std(ddata)/rootn)

end