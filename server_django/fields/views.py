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
        raise Http404("Not Supported Route: Use POST Method.")

    # handling post request
    try:
        field = Field.objects.filter(name=field_name)[0]
    except Field.DoesNotExist:
        raise Http404("Field does not exist")

    data = json.loads(request.body)
    field.value += data['value']
    field.save()

    return HttpResponse(
        json.dumps({'field': field.value}), content_type="application/json"
    )
