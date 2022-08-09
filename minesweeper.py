from tkinter import *
import random

game = Tk()

#Game Features
Width = 1440
Height = 720
Grid_Size = 6
Cell_Count = Grid_Size**2
Mines_Count = (Grid_Size**2)//4
class Cell:
    all = [] #List of Populated Cells
    cell_count_label = None
    left = Cell_Count - Mines_Count
    def __init__(self, x, y, is_mine=False):
        self.is_mine = is_mine
        self.is_open = False
        self.cell_button = None
        self.x = x
        self.y = y
        Cell.all.append(self)

    def left_click(self, event):
        if self.is_mine:
            self.show_mine()
        else:
            self.show_cell()
            if self.surrounding_mine_count == 0:
                for cell in self.actual_cells:
                    cell.show_cell()

    def right_click(self, event):
        print(event)
        print("I am right clicked")

    @staticmethod
    def randomize_mines(): #Randomizes location of mines
        mines = random.sample(Cell.all, Mines_Count)
        for mine in mines:
            mine.is_mine = True
    def __repr__(self): #Identify mines by Grid Location
        return f'Cell({self.x}, {self.y})'

    def show_mine(self):
        self.cell_button.configure(bg = 'red')

    def get_cell_by_grid(self, x, y):
        #Returning a cell object by grid position
        for cell in Cell.all:
            if cell.x == x and cell.y == y:
                return cell

    def show_cell(self):
        if self.is_open != True:
            surrounding_cells = [
                self.get_cell_by_grid(self.x - 1, self.y - 1),
                self.get_cell_by_grid(self.x - 1, self.y),
                self.get_cell_by_grid(self.x - 1, self.y + 1),
                self.get_cell_by_grid(self.x, self.y - 1),
                self.get_cell_by_grid(self.x + 1, self.y - 1),
                self.get_cell_by_grid(self.x + 1, self.y),
                self.get_cell_by_grid(self.x + 1, self.y + 1),
                self.get_cell_by_grid(self.x, self.y + 1),
            ]

            actual_cells = []
            for cell in surrounding_cells:
                if cell != None:
                    actual_cells.append(cell)
            self.actual_cells = actual_cells
            surrounding_mines = []
            for cell in actual_cells:
                if cell.is_mine:
                    surrounding_mines.append(cell)
            self.surrounding_mine_count = len(surrounding_mines)
            self.cell_button.configure(text = self.surrounding_mine_count)

            Cell.left -= 1
            if Cell.cell_count_label:
                Cell.cell_count_label.configure(
                    text = f'Cells Left: {Cell.left}'
                )
            self.is_open = True

    def create_button(self, location):
        self.cell_button = Button(
            location,
            width = 12,
            height = 4,
        )
        self.cell_button.bind('<Button-1>', self.left_click)
        self.cell_button.bind('<Button-3>', self.right_click)

    @staticmethod
    def create_cell_count_label(location):
        lbl = Label(
            location,
            bg = 'black',
            fg = 'white',
            text = f'Cells Left: {Cell.left}',
            font = ('', 30)
        )
        Cell.cell_count_label = lbl


#Utility Functions
def height_prct(percentage):
    return Height*(percentage/100)

def width_prct(percentage):
    return Width*(percentage/100)

#Settings of the Window
game.configure(bg='black') #Color of the Background
game.geometry(f'{Width}x{Height}') #Size of the Window
game.title('Minesweeper') #Title of the Window
game.resizable(False, False) #Window not resizable

#Settings of Top Frame
top_frame = Frame(
    game,
    bg = 'black', #Color of TopFrame
    width = Width, #Width of window
    height = height_prct(25) #Height of window
)
top_frame.place(x=0, y=0) #Location of TopLeft Corner of window for Top Frame

#Settings of Side Frame
side_frame = Frame(
    game,
    bg = 'black', #Color of TopFrame
    width = width_prct(25), #Width of window
    height = height_prct(75) #Height of window
)
side_frame.place(x=0, y=height_prct(25)) #Location of TopLeft Corner of window for Side Frame

#Settings of Center Frame
center_frame = Frame(
    game,
    bg = 'black',
    width = width_prct(75),
    height = height_prct(75)
)
center_frame.place(x=width_prct(25), y=height_prct(25))

#Setting Label
Cell.create_cell_count_label(side_frame)
Cell.cell_count_label.place(
    x=0, y=0
)

#Populating Cells
for x in range(Grid_Size):
    for y in range(Grid_Size):
        c = Cell(x, y)
        c.create_button(center_frame)
        c.cell_button.grid(
            column = y, row = x
        )


Cell.randomize_mines()

#Code that Runs the Window
game.mainloop()
