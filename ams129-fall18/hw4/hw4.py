"""
Nethaniel Sanchez
Ams129 Fall 2018
Negmsanc@ucsc.edu
"""


def right_justify(S):
    length = len(S)
    result = ""
    space = " "
    spaces = 70 - length
    for x in range(spaces):
        result = result + space
    result = result + S
    print(result)

def count_char(s, c):
    s = s.lower()
    count = 0
    for x in s:
        if x == c:
            count = count + 1
    print("Total number of %s in the given string: %d" % (c, count))

def cumulative_sum(l):
    result = "["
    count = 0;
    length = 0;
    for x in l:
        count = count + x
        result = result + "%d, " % (count)
    length = len(result)
    result = result[0:length-2]
    result = result + "]"
    return result

def check_palindrome(s):
    length = len(s)
    backward_s = ""
    for x in s:
        backward_s = x + backward_s
    if s == backward_s:
        return True
    else:
        return False

def main():
    right_justify("Nethaniel Sanchez")

    lorem = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla iaculis tellus id mollis scelerisque. In '
    'consequat nec tellus lacinia iaculis. Mauris auctor volutpat aliquam. Nulla dignissim arcu placerat tellus pretium'
    ', vel venenatis sem porta. Donec interdum tincidunt mi, et convallis velit aliquet eget. Nam id rutrum felis. '
    'Fusce vel fermentum justo. Pellentesque faucibus orci at velit vehicula dignissim. Duis faucibus dapibus malesuada'
    '. Pellentesque iaculis tristique vestibulum. Sed egestas nisl non augue imperdiet mattis. Nunc vitae purus lectus.'
    'Vestibulum mi turpis, volutpat vel odio quis, hendrerit suscipit ligula. Nunc in massa diam. Suspendisse aliquam'
    'quam et ex egestas vestibulum vitae id libero. Cras molestie consectetur condimentum.'
    count_char(lorem, 't')

    result = cumulative_sum([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    print(result)

    words_list = ['noon', 'madam', 'ams129', 'redivider', 'numpy', 'bob', 'racecar', 'youngjun']
    for word in words_list:
        if(check_palindrome(word)):
            print(word, 'is palindrome!')

if __name__ == "__main__": main()
