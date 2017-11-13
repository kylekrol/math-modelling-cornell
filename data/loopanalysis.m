close all;

% graph gas usage over a single route

graphsteps('stp/rt10.txt',1)
title('Route 10: Incremental Gas Consumption')
graphsteps('stp/rt11.txt',2)
title('Route 11: Incremental Gas Consumption')
graphsteps('stp/rt15.txt',3)
title('Route 15: Incremental Gas Consumption')
graphsteps('stp/rt17.txt',4)
title('Route 17: Incremental Gas Consumption')
graphsteps('stp/rt81.txt',5)
title('Route 81: Incremental Gas Consumption')
graphsteps('stp/rt82.txt',6)
title('Route 82: Incremental Gas Consumption')

function graphsteps(file, i)
    
    subplot(2,3,i)
    data = csvread(file);
    bar(data(:,1))
    
    xlabel('Route Progression')
    ylabel('Gas Consumed (gal)')
    %title('Gas Usage per Hour By Route')
    
end