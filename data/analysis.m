close all;

plotloopmpg('rt10mpg.txt',1)

function plotloopmpg(file, i)

    figure(i)
    data = csvread(file, 2, 999);
    plot(data(1,:),'b-')
    plot(data(2,:),'g-')

end