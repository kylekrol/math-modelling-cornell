close all;

figure(1)
hold on;

data = [
    .783,.376;
    1.483,.754;
    .936,.444;
    .404,.227;
    .879,.349;
    1.695,.796;
];

data = [data (data(:,1)-data(:,2))];

time = [
    19;
    51;
    34;
    12;
    35;
    58
];
time = time/60;

data = data./time

figure(1)
bar(1:6,data)
grid on;
set(gca,'XTickLabel',['     ';'Rt 10';'Rt 11';'Rt 15';'Rt 17';'Rt 81';'Rt 82'])
title('Gas Usage per Hour By Route')
legend('Diesel','Hybrid','Difference')
xlabel('Route')
ylabel('Gas Used (gal/hr)')