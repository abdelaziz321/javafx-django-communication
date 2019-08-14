from . import views
from django.urls import path


app_name = 'fields'

urlpatterns = [
    path('', views.get_fields, name='getValues'),
    path('<field_name>', views.update_field, name='updateField')
]

