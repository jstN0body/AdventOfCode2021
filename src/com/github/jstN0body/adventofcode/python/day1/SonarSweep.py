import sys

inputData = sys.argv
inputData.remove(inputData[0])

total = 0
for index in range(len(inputData)):
    if index >= 1:
        value = int(inputData[index])
        prevValue = int(inputData[index - 1])
        if value > prevValue:
            total += 1

print("{} total increases".format(total))

sumIncreases = 0
for i in range(len(inputData)):
    if i >= 1 and not (i + 2) < len(inputData):
        continue
    prevSum = int(inputData[i - 1]) + int(inputData[i]) + int(inputData[i + 1])
    currentSum = int(inputData[i]) + int(inputData[i + 1]) + int(inputData[i + 2])
    if currentSum > prevSum:
        sumIncreases += 1

print("{} sum increases".format(sumIncreases))
