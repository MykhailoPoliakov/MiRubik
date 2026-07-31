import numpy as np

def convert_obj(file_name) -> tuple:
    """
    Converts file.obj to lists of points and colored polygons.

    Args:
            file_name (str): name of the .obj file
    Returns:
        ( 
            points : (np.array[ x,y,z ]) , 
            colored_polygons : (list[ tuple[ points` indexes for polygon ] , list[ color ] )
        )
    """
    color: str = ''
    points_list: list = []
    colored_polygons = []
    # open .obj file
    with open(file_name, 'r') as file:
        for index, line in enumerate(file):

            # if no line is empty
            if not line or line.startswith('#'):
                continue
            words: list = line.split()
            if not words:
                continue

            # match first word
            match words[0]:
                # point line
                case 'v':
                    points = [round(float(words[1]), 2), round(float(words[2]), 2), round(float(words[3]), 2)]
                    points_list.append(points)
                # color line
                case 'usemtl':
                    color = words[1]
                # polygon line
                case 'f':
                    polygon_list = []
                    points_amount = len(words) - 1
                    for _index in range(points_amount):
                        polygon_list.append(int(words[_index + 1].split('/')[0]))
                    polygon = tuple(polygon_list)
                    colored_polygons.append([polygon, color])

    # convert to an array
    points_array = np.array(points_list, dtype=np.float32)

    return (points_array, colored_polygons)


