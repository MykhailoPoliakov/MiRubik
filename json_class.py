import json
import os

class Json:
    """
        self.data           to change data
        self.update_data()  to save changes to the json file
    """
    def __init__(self, name: str, file_name : str, default_dict: dict):
        self.__name = name
        self.__file_name = file_name
        self.__default_dict = default_dict
        # get user data from the file
        self.data = self.__get_data
        self.update_data()

    @property
    def __path(self):
        """ get path that works with compiling """
        save_dir = os.path.join(os.getenv("LOCALAPPDATA"), self.__name)
        os.makedirs(save_dir, exist_ok=True)
        return os.path.join(save_dir, self.__file_name)

    @property
    def __get_data(self):
        """ get data from json file, if no data, get data from default dict """
        try:
            with open(self.__path, "r", encoding="utf-8") as json_file:
                return json.load(json_file)
        except (FileNotFoundError, json.JSONDecodeError):
            return self.__default_dict

    def update_data(self) -> None:
        """ after changing self.data update date in json file """
        with open(self.__path, "w", encoding="utf-8") as _json_file:
            json.dump(self.data, _json_file, ensure_ascii=False, indent=4, sort_keys=True)