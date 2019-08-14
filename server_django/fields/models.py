import datetime
from django.db import models
from django.utils import timezone


class Field(models.Model):
    name = models.CharField(max_length=2)
    value = models.IntegerField(default=0)