describe("Audition JavaScript Tests", function() {

  it("SpecRunner runs", function() {
    expect(JavaScriptAudition.itRuns()).toBeTruthy();
  });


  describe("Canvas tests", function() {
    var a = new initDisplay(640,640);

    it ("should run",function () {
      expect(true).toBeTruthy();
    });

    it ("should have cells width passed",function() {
      expect(a.cells_w).toEqual(640);
    });

    it("should have cells height passed",function () {
      expect(a.cells_h).toEqual(640);
    });
  });

  describe("Cell funcitons", function () {
    var foo;
    var c =new Cell(50,50);

    beforeEach(function () {
      foo = {
        birth: function() {return "Hi";},
        death: function() {return "bye:(";}
      }

      spyOn(foo, "birth");
      spyOn(foo, "death");
      foo.birth();
      c.birth();
    });

    it("should have x and y coords", function () {
      expect(c.x).toEqual(50);
      expect(c.y).toEqual(50);
    });

    it("tracks that birth was called", function() {
       expect(foo.birth).toHaveBeenCalled();
    });

    it("should have size of 5", function() {
      expect(c.size).toEqual(5);
    });

    it("should create new cell that is green", function () {
      c.birth();
      expect(ctx.fillStyle).toEqual("#00ff00");
    });

    it("should change the color of the cell to black", function () {
      c.death();
      expect(ctx.fillStyle).toEqual("#000000");
    });
  });

  describe("Seutp the rest of the display items", function() {
    var g = [];

    for (var i = 0; i < 3; i++)
      g[i] = [];

    for (var x =0; x < 3; x++){
      for (var y = 0; y < 3; y++)
        g[x][y] = Math.round(Math.random());
    }

    it("should produce an array of length = 148", function() {
      var g = createGrid(148);
      expect(g.length).toEqual(148);
    });
  });

  describe("Game Logic", function() {
    var g = [];
    var g2 = []
    for (var i = 0; i < 3; i++)
      g[i] = [];

    for (var x =0; x < 3; x++){
      for (var y = 0; y < 3; y++)
        g[x][y] = Math.round(Math.random());
    }

    it("populate randomly around the grid either 1 or 0", function() {
      expect(g[0][0]).toBeLessThan(2);
    });

    it("should set all grid cells to 0", function() {
      for (var x =0; x < g.length-1; x++){
        for (var y = 0;y < g.length-1; y++)
          g[x][y] = 0;
      }
       for (var x =0; x < g.length-1; x++){
        for (var y = 0;y < g.length-1; y++)
          expect(g[x][y]).toEqual(0);
      }
    });

    it("should check neighbor cells  ", function() {
      var sum = 0;
      for (var x =1; x < 2; x++){
        for (var y = 1; y< 2; y++){

          sum += g[x-1][y-1];
          sum += g[x-1][y];
          sum += g[x-1][y+1];

          sum += g[x][y+1];
          sum += g[x][y-1];

          sum += g[x+1][y-1];
          sum += g[x+1][y];
          sum += g[x+1][y+1];

        }
      }

     expect(sum).toBeGreaterThan(0);
     expect(sum).toBeLessThan(10);
    });

  });

});
