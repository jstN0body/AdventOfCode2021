import sys

inputData = sys.argv
inputData.remove(inputData[0])

depth = 0
dist = 0
for i in range(len(inputData)):
    if i + 1 == len(inputData):
        continue
    action = inputData[i]
    i += 1
    amount = inputData[i]
    if action == "forward":
        dist += int(amount)
    elif action == "up":
        depth -= int(amount)
    elif action == "down":
        depth += int(amount)

print("Depth: {}".format(depth))
print("Dist: {}".format(dist))
print("Answer: {}\n".format(depth * dist))

depth = 0
dist = 0
aim = 0

for i in range(len(inputData)):
    if i + 1 == len(inputData):
        continue
    action = inputData[i]
    i += 1
    amount = inputData[i]
    if action == "forward":
        dist += int(amount)
        depth += int(amount) * aim
    elif action == "up":
        aim -= int(amount)
    elif action == "down":
        aim += int(amount)

print("Depth: {}".format(depth))
print("Dist: {}".format(dist))
print("Answer: {}".format(depth * dist))
