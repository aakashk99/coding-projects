import random
import curses


#Initializes a screen with no cursor
screen = curses.initscr()
curses.curs_set(0)

#Creates a window with a Maximum Screen Width and Height
screenheight, screenwidth = screen.getmaxyx()
window = curses.newwin(screenheight, screenwidth, 0, 0)

#Accepts keyboard input and the screen refreshes every 100 ms
window.keypad(1)
window.timeout(100)

#Initializing Snake
snake_x = screenwidth/4
snake_y = screenheight/2
snake = [
    [snake_y, snake_x],
    [snake_y, snake_x-1],
    [snake_y, snake_x-2]
]

#Creating Food
food = [screenheight/2, screenwidth/2]
window.addch(int(food[0]), int(food[1]), curses.ACS_DIAMOND)

#Creating Walls
wall_list = []
for i in range (50):
    wall = [random.randint(0, screenheight-1), random.randint(0, screenwidth-1)]
    wall_list.append(wall)
    window.addch(int(wall[0]), int(wall[1]), curses.ACS_BLOCK)

#Set intial direction of the snake to moving right
key = curses.KEY_RIGHT

while True:
    next_key = window.getch()
    if next_key == -1:
        key = key
    else:
        key = next_key

    if snake[0][0] in [0, screenheight] or snake[0][1]  in [0, screenwidth] or snake[0] in snake[1:] or snake[0] in wall_list:
        curses.endwin()
        quit()

    #Create new head for snake
    new_head = [snake[0][0], snake[0][1]]

    #Movement of Snake
    if key == curses.KEY_DOWN:
        new_head[0] += 1
    if key == curses.KEY_UP:
        new_head[0] -= 1
    if key == curses.KEY_LEFT:
        new_head[1] -= 1
    if key == curses.KEY_RIGHT:
        new_head[1] += 1

    #Adding Head to Snake
    snake.insert(0, new_head)

    #Making Snake grow with Food
    if snake[0] == food:
        food = None
        while food is None:
            new_food = [
                random.randint(1, screenheight-1),
                random.randint(1, screenwidth-1)
            ]
            if new_food not in snake:
                food = new_food
            else:
                food = None
        window.addch(food[0], food[1], curses.ACS_DIAMOND)
    else:
        tail = snake.pop()
        window.addch(int(tail[0]), int(tail[1]), ' ')

    window.addch(int(snake[0][0]), int(snake[0][1]), curses.ACS_CKBOARD)
