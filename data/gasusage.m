close all;

% gas usage data processing

figure(1)
hold on;

data = [
    .572,.350;
    1.115,.718;
    .804,.440;
    .361,.220;
    .558,.306;
    1.281,.754;
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
title('Gas Usage per Hour By Route (Summer)')
legend('Diesel','Hybrid','Difference')
xlabel('Route')
ylabel('Gas Used (gal/hr)')