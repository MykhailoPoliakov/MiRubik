import pygame
import sys, os

pygame.init()
pygame.display.set_mode((1, 1))

""" Working Path """

def resource_path(relative_path):
    if hasattr(sys, '_MEIPASS'):
        return os.path.join(sys._MEIPASS, relative_path) # pyright: ignore[reportAttributeAccessIssue]
    return relative_path

# Textures

def get_texture(texture):
    return pygame.image.load(resource_path( f"assets/textures/{texture}") ).convert_alpha()

textures = {
    'fade'         : get_texture("fade.png"),
    'win_fade'     : get_texture("win_fade.png"),
    'background'   : get_texture("lake.jpg"),
    'black'        : get_texture("black.png"),
    # top menu
    'menu'         : get_texture("menu.png"),
    'left_button'  : get_texture("left_button.png"),
    'right_button' : get_texture("right_button.png"),
    'corner_button': get_texture("corner_button.png"),
    # sound textures
    'sound_on'     : get_texture("sound_on.png"),
    'sound'        : get_texture("sound.png"),
}
textures['background'] = pygame.transform.scale(textures['background'], (1920, 1080))
textures['fade'] = pygame.transform.scale(textures['fade'], (1920, 1080))
textures['win_fade'] = pygame.transform.scale(textures['win_fade'], (1920, 1080))
textures['black'].set_alpha(60)
textures['win_fade'].set_alpha(60)


# Sounds

sounds = {
    'click': pygame.mixer.Sound("assets/sounds/click.wav"),
    'select': pygame.mixer.Sound("assets/sounds/select.wav"),
}


# Fonts

bold_fonts = {
    'sans': "assets/fonts/sans.ttf",
    'cosmo': "assets/fonts/cosmo.otf",
}
fonts = {
    'cosmo170' : pygame.font.Font( bold_fonts["cosmo"] , 170),
    'cosmo80'  : pygame.font.Font( bold_fonts["cosmo"] , 80),
    'sans40'   : pygame.font.Font( bold_fonts["sans"] , 40),
    'None40'   : pygame.font.Font( None , 40),
}
texts = {
    'play'    : fonts['cosmo170'].render("play", True, (255, 255, 255)),
    'inspect' : fonts['cosmo80'].render("INSPECT", True, (255, 255, 255))
}
