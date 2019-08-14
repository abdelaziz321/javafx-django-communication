import json
from .models import Field
from django.http import Http404
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt


@csrf_exempt
def get_fields(request):
    fields = {}
    for field in Field.objects.all():
        fields[field.name] = field.value

    return HttpResponse(
        json.dumps(fields), content_type="application/json"
    )


@csrf_exempt
def update_field(request, field_name):
    if request.method != 'POST':
        raise Http404("Question does not exist")

    # handling post request
    try:
        field = Field.objects.filter(name='A')[0]
    except Field.DoesNotExist:
        raise Http404("Field does not exist")

    field.value += int(request.POST.get('value', 0))
    field.save()

    return HttpResponse(
        json.dumps({'field': field.value}), content_type="application/json"
    )
