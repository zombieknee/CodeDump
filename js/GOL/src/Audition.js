"use strict";

var JavaScriptAudition = {
  itRuns: function() {
    return true;
  }
};



// freq used vars
var cells_w = 640;
var cells_h = 640;
var mainGrid = createGrid(128);
var flipGrid = createGrid(128);
var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");
var running = false;

function initDisplay(cells_w, cells_h){
    this.cells_w = cells_w;
    this.cells_h = cells_h;

    ctx.fillRect(0,0,cells_w,cells_h);
    drawGrid();
}

function Cell(cell_x,cell_y) {
  this.x = cell_x;
  this.y = cell_y;
  this.size = 5;



  Cell.prototype.death = function() { // turn cell OFF
    ctx.fillStyle ="#000000"; //black
    ctx.fillRect(this.x*this.size, this.y * this.size, this.size, this.size);
  }

  Cell.prototype.birth = function( ) {// turn cell ON
    ctx.fillStyle = "#00ff00"; //green
    ctx.fillRect(this.x*this.size+1, this.y * this.size+1, this.size-2, this.size-2);
  }
}

function createGrid(rows) {
  var grid = [];
  for (var i = 0; i < rows; i++)
    grid[i] = [];
  return grid;
}

function drawGrid() {
  ctx.clearRect(0,0, cells_w,cells_h);
  for (var x = 0; x < mainGrid.length; x++){
    for (var y = 0; y <mainGrid.length; y++) {
      var cell = new Cell(x,y);
      if (mainGrid[x][y] == 1)
        cell.birth();
      else if (mainGrid[x][y] == 0)
        cell.death();
    }
  }
}

function clearGrid() {
  for (var x = 0; x < mainGrid.length-1; x++){
    for (var y = 0; y < mainGrid.length-1; y++){
      mainGrid[x][y] = 0;
      flipGrid[x][y] = 0;
    }
  }
  stop();
  drawGrid();
}

function updateGrid() {
  // update grid between mainGrid and flipGrid
  for (var x = 1; x < mainGrid.length-1; x++){
    for (var y = 1; y < mainGrid.length-1; y++){
      var numCells = 0;
      // sums  for cell population
      numCells += mainGrid[x -1][y - 1];
      numCells += mainGrid[x -1][y];
      numCells += mainGrid[x -1][y +1];

      numCells += mainGrid[x][y + 1];
      numCells += mainGrid[x][y - 1];

      numCells += mainGrid[x + 1][y - 1];
      numCells += mainGrid[x + 1][y];
      numCells += mainGrid[x + 1][y +1];

      if (numCells == 2)
        flipGrid[x][y] = mainGrid[x][y]
      else if (numCells == 3)
        flipGrid[x][y] = 1;
      else
        flipGrid[x][y] = 0;
    }
  }

  var tmp = mainGrid;
  mainGrid = flipGrid;
  flipGrid = tmp;
}

function fillRandom() {
  // random 'birth' of cells
    clearGrid();
  for (var x = 10; x < mainGrid.length-10; x++){
    for (var y = 10 ; y < mainGrid.length-10; y++){
      mainGrid[x][y] = Math.round(Math.random());
    }
  }
  stop();

  drawGrid();
}

function Gosper() {
  clearGrid();
  mainGrid[7][20] = 1;
  mainGrid[7][21] = 1;
  mainGrid[8][20] = 1;
  mainGrid[8][21] = 1;
  mainGrid[17][20] = 1;
  mainGrid[17][21] = 1;
  mainGrid[17][22] = 1;
  mainGrid[18][19] = 1;
  mainGrid[18][23] = 1;
  mainGrid[19][18] = 1;
  mainGrid[19][24] = 1;
  mainGrid[20][18] = 1;
  mainGrid[20][24] = 1;
  mainGrid[21][21] = 1;
  mainGrid[22][19] = 1;
  mainGrid[22][23] = 1;
  mainGrid[23][20] = 1;
  mainGrid[23][21] = 1;
  mainGrid[23][22] = 1;
  mainGrid[24][21] = 1;
  mainGrid[27][18] = 1;
  mainGrid[27][19] = 1;
  mainGrid[27][20] = 1;
  mainGrid[28][18] = 1;
  mainGrid[28][19] = 1;
  mainGrid[28][20] = 1;
  mainGrid[29][17] = 1;
  mainGrid[29][21] = 1;
  mainGrid[31][16] = 1;
  mainGrid[31][17] = 1;
  mainGrid[31][21] = 1;
  mainGrid[31][22] = 1;
  mainGrid[41][18] = 1;
  mainGrid[41][19] = 1;
  mainGrid[42][18] = 1;
  mainGrid[42][19] = 1;
  stop();

  drawGrid();
}

function LWSS() {
  clearGrid();
  mainGrid[20][20] = 1;
  mainGrid[20][21] = 1;
  mainGrid[20][22] = 1;
  mainGrid[21][19] = 1;
  mainGrid[21][22] = 1;
  mainGrid[22][22] = 1;
  mainGrid[23][22] = 1;
  mainGrid[24][19] = 1;
  mainGrid[24][21] = 1;
  stop();
  drawGrid();
}

function gameLoop() {
  // main loop
  if(running){
    drawGrid();
    updateGrid();
    requestAnimationFrame(gameLoop);
  }
}

function stop() {
  // stops loop
  var b = document.getElementById("start");
  b.disabled = false;
  running = false;
}

function start() {
  // start loop
  console.log("TRYING TO start");
  var b = document.getElementById("start");
  b.disabled = true;
  running = true;
  gameLoop();
}

function step() {
  // one step generation
  drawGrid();
  updateGrid();
}

var disp = new initDisplay(cells_w,cells_h,"myCanvas",true);
