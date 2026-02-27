import math
import copy

class Element:
    invert_index = { 0 : (1, 2), 1 : (0, 2), 2 : (0, 1)}

    def __init__(self, name: str, polygons: tuple[tuple,tuple], points: dict[str,list], visibility: bool):
        # main properties
        self.name: str = name
        self.points: dict[str,list] = points
        self.rotation: list = [0, 0, 0]
        self.polygons = polygons
        self.visibility: bool = visibility
        # saving start points position
        self.__points_copy = copy.deepcopy(self.points)

    def reset(self) -> None:
        """ reset the object """
        self.rotation = [0, 0, 0]
        self.points = copy.deepcopy(self.__points_copy)

    def rotate(self, index: int, add_angle: int | float) -> None:
        """ rotate object """
        def angle_calc(_radius, cord_0, cord_1):
            """ get angle of the point to the center """
            _angle = math.degrees(math.acos( cord_1 / _radius )) if _radius != 0 else 0
            return 360 - _angle if cord_0 < 0 else _angle

        # inverted index
        inv_index: tuple = self.invert_index[index]
        # for all object`s points
        for _point in self.points:
            # get radius
            radius = math.hypot(self.points[_point][inv_index[0]], self.points[_point][inv_index[1]])
            # get angle
            angle = angle_calc(radius, self.points[_point][inv_index[0]], self.points[_point][inv_index[1]]) - add_angle
            # update points dictionary
            self.points[_point][inv_index[0]] = round(radius * math.sin(math.radians(angle)), 2)
            self.points[_point][inv_index[1]] = round(radius * math.cos(math.radians(angle)), 2)

        # update object`s rotation angle
        self.rotation[index] -= add_angle
        if self.rotation[index] >= 360:
            self.rotation[index] -= 360
        if self.rotation[index] < 0:
            self.rotation[index] += 360
